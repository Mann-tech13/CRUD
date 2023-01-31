/**
 * 
 */

let dataLength = 5;
let dataStart = 0;
let dataEnd = 6;
let active;
let flag = false;
document.querySelector(".tdata").innerHTML = showFromDB(dataStart, dataLength);


function saveClick(e) {
	let emp_name = document.querySelector(".enter-name").value;
	let dep_name = document.querySelector(".enter-depName").value;
	let emp_id = document.querySelector(".enter-empId").value;
	let dep_id = document.querySelector(".enter-depId").value;

	if (emp_name == "") {
		alert("Enter Your Name");
		return false;
	}
	else if (dep_name == "") {
		alert("Enter Your Department Name");
		return false;
	}
	else if (emp_id == "") {
		alert("Enter Your Unique Employee Id");
		return false;
	}
	else if (dep_id == "") {
		alert("Enter Your Department Id");
		return false;
	}
	let object = {
		E_name: emp_name,
		D_name: dep_name,
		EID: emp_id,
		DID: dep_id,
	}

	$.ajax({
		url: "EmployeeRegister",
		data: object,
		type: "POST",
		success: function(data, textStatus, jqXHR) {
			clearClick();
			showFromDB(dataStart, dataLength);


		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("pARENT ERROR");
			console.log(errorThrown);

		},

	})
}

function clearClick() {
	document.querySelector(".enter-name").value = "";
	document.querySelector(".enter-depName").value = "";
	document.querySelector(".enter-empId").value = "";
	document.querySelector(".enter-depId").value = "";
}

function editClickFunction(EId, EName, DId, DName) {
	document.querySelector(".enter-name").value = EName;
	document.querySelector(".enter-depName").value = DName;
	document.querySelector(".enter-empId").value = EId;
	document.querySelector(".enter-depId").value = DId;

	deleteAfterEdit(EId)
}

function deleteAfterEdit(EId) {
	$.ajax({
		url: "DeleteServlet",
		data: EId,
		type: "POST",
		success: function(data, textStatus, jqXHR) {
			console.log("Delete SUccess");

		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("Delete Error");
		}
	})
}

function deleteClickFunction(EId) {
	dataStart = Number(document.querySelector(".active").getAttribute("data-start"));
	$.ajax({
		url: "DeleteServlet",
		data: EId,
		type: "POST",
		success: function(data, textStatus, jqXHR) {
			console.log("Delete SUccess", dataStart);
			//	getPageDataCount();
			showFromDB(dataStart, dataLength);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("Delete Error");
		}
	})
}

function showFromDB(start, end) {
	console.log(start, end)
	let x = 0;
	let dynamicLength = end;
	if (start == 0) {
		x = 1;
		dynamicLength++;
	}
	let data = {
		start: start,
		end: dynamicLength,
	}

	$.ajax({
		url: "ShowData",
		data: data,
		type: "GET",
		success: function(data, textStatus, jqXHR) {
			let parent = document.querySelector(".tdata");
			parent.innerHTML = "";
			let table = document.createElement("table");
			table.classList.add("table-data");
			let tableRowHead = document.createElement("tr");
			let tableHeadEID = document.createElement("th");
			let tableHeadDID = document.createElement("th");
			let tableHeadENAME = document.createElement("th");
			let tableHeadDNAME = document.createElement("th");
			let tableHeadOption = document.createElement("th");
			table.appendChild(tableRowHead);


			tableHeadEID.innerHTML = "Employee ID";
			tableRowHead.appendChild(tableHeadEID);
			tableHeadENAME.innerHTML = "Employee Name";
			tableRowHead.appendChild(tableHeadENAME);
			tableHeadDID.innerHTML = "Department ID";
			tableRowHead.appendChild(tableHeadDID);
			tableHeadDNAME.innerHTML = "Department Name";
			tableRowHead.appendChild(tableHeadDNAME);
			tableHeadOption.innerHTML = "Edit Data";
			tableRowHead.appendChild(tableHeadOption);

			let tableBody = document.createElement("tbody");
			table.appendChild(tableBody);

			console.log("showdata: ", data);

			for (let i = x; i < data.length; i++) {
				let tableRow = document.createElement("tr");
				tableBody.appendChild(tableRow);
				tableRow.classList.add("rows");


				let tableDisplayEID = document.createElement("td");
				let tableDisplayDID = document.createElement("td");
				let tableDisplayENAME = document.createElement("td");
				let tableDisplayDNAME = document.createElement("td");
				let tableDisplayOperation = document.createElement("td");

				let edit = document.createElement("button");
				edit.classList.add("btn-ed");
				let editText = document.createTextNode("Edit");
				edit.name = data[i].E_ID + "_" + data[i].E_name + "_" + data[i].D_ID + "_" + data[i].D_name;
				edit.classList.add("edit-btn");
				edit.appendChild(editText);
				edit.addEventListener("click", function() { editClickFunction(data[i].E_ID, data[i].E_name, data[i].D_ID, data[i].D_name) });
				let deletebtn = document.createElement("button");
				deletebtn.classList.add("btn-ed");
				let deleteText = document.createTextNode("Delete");
				deletebtn.name = data[i].E_ID;
				deletebtn.classList.add("delete-btn");
				deletebtn.appendChild(deleteText);
				deletebtn.addEventListener("click", function() { deleteClickFunction(data[i].E_ID) });

				tableDisplayEID.innerHTML = data[i].E_ID;
				tableRow.appendChild(tableDisplayEID);
				tableDisplayENAME.innerHTML = data[i].E_name;
				tableRow.appendChild(tableDisplayENAME);
				tableDisplayDID.innerHTML = data[i].D_ID;
				tableRow.appendChild(tableDisplayDID);
				tableDisplayDNAME.innerHTML = data[i].D_name;
				tableRow.appendChild(tableDisplayDNAME);
				tableDisplayOperation.appendChild(edit);
				tableDisplayOperation.appendChild(deletebtn);
				tableRow.appendChild(tableDisplayOperation);
			}


			parent.appendChild(table)

			console.log("Child Success");

			getPageDataCount();



		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(data);
			console.log("Error....");

		},

	})
}
let page = 0;
let buttons = 0;
function getPageDataCount() {
	$.ajax({
		url: "GetCount",
		type: "GET",
		success: function(data, textStatus, jqXHR) {
			let divForPage = document.querySelector(".numbering");
			console.log("buttons", data)

			buttons = Math.ceil((data[0] - 1) / 5);  // 9 / 5 ~ 2
			console.log("page: ", page)
			console.log("buttons: ", buttons)
			if (page < buttons) {
				divForPage.innerHTML = "";
				for (let i = 0; i < buttons; i++) {
					let button = document.createElement('button');
					button.className = 'list';
					button.classList.add("list" + (i + 1).toString());
					button.innerHTML = i + 1;
					dataStart = ((i + 1) * dataLength) - dataLength + 1
					dataEnd = (i + 1) * 5
					button.setAttribute("data-start", dataStart.toString());
					//	button.setAttribute("data-end", dataEnd.toString());
					button.addEventListener("click", function() {
						let prevActive = document.querySelector(".active");
						if (prevActive != null) {
							prevActive.classList.remove("active")
						}
						button.classList.add("active");
						dataStart = Number(document.querySelector(".active").getAttribute("data-start"));
						showFromDB(Number(button.getAttribute("data-start")), dataLength);
					})
					divForPage.appendChild(button);
				}
				if(flag == false){
					document.querySelector(".list1").classList.add("active");
					flag = true;
				}
				else{
					let lastElement = Array.from(document.querySelectorAll('.list')).pop();
					lastElement.classList.add("active");
					
				}
			}
			else if (buttons < page) {
				divForPage.removeChild(divForPage.lastElementChild);
				let lastElement = Array.from(document.querySelectorAll('.list')).pop();
				lastElement.classList.add("active");
				showFromDB(lastElement.getAttribute("data-start"), dataLength);
			}
			page = buttons;

		},
		error: function(data, textStatus, jqXHR) {
		}
	})
}





