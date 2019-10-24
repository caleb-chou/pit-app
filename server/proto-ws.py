import os, sv, json, cgi
from http.server import BaseHTTPRequestHandler, HTTPServer

host_name = 'localhost'
port = 8080

class serverproto(BaseHTTPRequestHandler):
    def do_HEAD(self):
        self.send_response(200)
        self.send_header('Content-type', 'application/json')
        self.end_headers()
    def _redirect(self,path):
        self.send_response(303)
        self.send_header('Content-type', 'application/json')
        self.send_header('Location', 'application/json')
        self.end_headers()
    def do_GET(self):
        html = '''
        <html>
        <body>
        <p>Hello World!</p>
        <form action = "/" method = "POST">
            <input type = "submit" name = "submit" value = "a">
            <input type = "submit" name = "submit" value = "b">
        </form>
        </body>
        </html>
        '''
        self.do_HEAD()
        self.wfile.write(json.dumps({'hello':'world', 'recieved':'ok'}))
    def do_POST(self):
        ctype, pdict = cgi.parse_header(self.headers['Content-type'])
        if(ctype != 'application/json'):
            self.send_response(400)
            self.end_headers()
            return
        content_length = int(self.headers['Content-Length'])
        message = json.loads(self.rfile.read(content_length))
        message['recieved'] = 'ok'

        self.do_HEAD()
        self.wfile.write(json.dumps(message))
        self._redirect('/')

if (__name__ == '__main__'):
    http_server = HTTPServer((host_name,port), serverproto)
    print("Server Starts - %s:%s" % (host_name,port))

    try:
        http_server.serve_forever()
    except:
        http_server.server_close()