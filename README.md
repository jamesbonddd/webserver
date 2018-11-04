# webserver
Mini web server

## Add a new controller
+ extend from HttpServlet and implement the doGet method.
+ response can be rendered server side using mustache templating engine.
+ public files are by convention saved under folder: resources.
	+ sendView() looks for templates in the folder /resources/templates
	+ to serve static html files, put them under /resources/static

## Test
+ project is exported in an executable jar file (server.jar)
+ test: try: localhost:3000/home?name=yourname&age=yourage
