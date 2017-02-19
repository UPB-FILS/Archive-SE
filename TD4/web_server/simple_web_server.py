#!/usr/bin/python
from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
from os import curdir, sep
import cgi
import Queue

PORT_NUMBER = 8000

#This class will handles any incoming request from the browser 
class myHandler(BaseHTTPRequestHandler):

	def __init__(self, nsa_queue, *args):
		self.nsa_queue = nsa_queue
		BaseHTTPRequestHandler.__init__(self, *args)

	#Handler for the GET requests
	def do_GET(self):
		if self.path=="/":
			self.path="/index.html"

		try:
			#Check the file extension required and set the right mime type

			sendReply = False
			if self.path.endswith(".html"):
				mimetype='text/html'
				sendReply = True
			if self.path.endswith(".jpg"):
				mimetype='image/jpg'
				sendReply = True
			if self.path.endswith(".gif"):
				mimetype='image/gif'
				sendReply = True
			if self.path.endswith(".js"):
				mimetype='application/javascript'
				sendReply = True
			if self.path.endswith(".css"):
				mimetype='text/css'
				sendReply = True

			if sendReply == True:
				#Open the static file requested and send it
				f = open(curdir + sep + self.path) 
				self.send_response(200)
				self.send_header('Content-type',mimetype)
				self.end_headers()
				self.wfile.write(f.read())
				f.close()
			return

		except IOError:
			self.send_error(404,'File Not Found: %s' % self.path)
	#Handler for the POST requests
	def do_POST(self):
		if self.path=="/send":
			form = cgi.FieldStorage(
				fp=self.rfile, 
				headers=self.headers,
				environ={'REQUEST_METHOD':'POST',
		                 'CONTENT_TYPE':self.headers['Content-Type'],
			})

			self.nsa_queue.put(form["le_texte"].value[::-1])
			print "Le texte en clair: %s" % form["le_texte"].value
			self.send_response(200)
			self.end_headers()
			self.wfile.write(form["le_texte"].value[::-1])
			return			

		if self.path=="/decrypt":
			le_texte = self.nsa_queue.get()
			print "Le texte en encode: %s" % le_texte
			self.send_response(200)
			self.end_headers()
			self.wfile.write("Le texte intercepte: %s." % le_texte)
			self.wfile.write(" Le texte decode: %s" % le_texte[::-1])
			return			
			
try:
	nsa_queue = Queue.Queue()

	def handler(*args):
		myHandler(nsa_queue, *args)
	
	#Create a web server and define the handler to manage the incoming request
	server = HTTPServer(('', PORT_NUMBER), handler)
	print 'Started httpserver on port ' , PORT_NUMBER
	#Wait forever for incoming http requests
	server.serve_forever()

except KeyboardInterrupt:
	print '^C received, shutting down the web server'
	server.socket.close()
