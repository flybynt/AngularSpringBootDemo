// Common set-up & resources
//
// Anonymous auto-executing JS function to load the common CSS and responsive design elements <head> element
(function (){
	// Inject a viewport to the <HEAD> element in order to ensure mobile layout & rendering
	var e = document.createElement("meta");
	e.setAttribute("name", "viewport");
	e.setAttribute("content", "width=device-width, initial-scale=1.0");	
    document.getElementsByTagName("head")[0].appendChild(e);
    
    // Inject common CSS resources to the <HEAD> element	
    e = document.createElement("link");
    e.setAttribute("href", "/css/Common.css");
    e.setAttribute("rel", "stylesheet");
    document.getElementsByTagName("head")[0].appendChild(e);
})();

//
// Commonly used utility functions that can be used w/o need for a framework
//

// Create the specified html tag with the provided content and append it to the identified element
function CreateAndAppendElement (parentId, childId, elementType, html) {
	var e = document.createElement(elementType);
	e.setAttribute("id", childId);
	e.innerHTML = html;
	document.getElementById(parentId).appendChild(e);	
}

// Inject an additional CSS file via DOM into header
function injectCss (uri) {
	var e = document.createElement("link");
    e.setAttribute("href", uri);
    e.setAttribute("rel", "stylesheet");
    document.getElementsByTagName("head")[0].appendChild(e);
}

// Inject an additional JS script via DOM to the <BODY> element
function injectScript (uri) {
	var e = document.createElement("script");
    e.setAttribute("src", uri);
    e.setAttribute("type", "text/javascript");
    document.getElementsByTagName("body")[0].appendChild(e);
}