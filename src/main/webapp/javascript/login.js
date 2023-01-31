/**
 * 
 */

function login() {
	let name = document.querySelector(".enter-name").value;
	let password = document.querySelector(".enter-pwd").value;
	let object = {
		name: name,
		password: password
	}
	$.ajax({
		url: "LoginServlet",
		data: object,
		type: "POST",
		success: function(data, textStatus, jqXHR) {
			let x = document.querySelector("form");
			x.action = LoginServlet
			console.log(x);
			console.log("success");
		},
		error: function(data, textStatus, jqXHR) {
		}
	})
	
}