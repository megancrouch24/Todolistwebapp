
//READ ALL

const taskrefresh = document.querySelector("#tasks");

const readmethod = () => {
fetch("http://localhost:8080/items/readAll") 
 .then((response) => {
   taskrefresh.innerHTML = " ";
   if (response.status !== 200) {
     console.error(`status: ${response.status}`);
     return;
   }
   response.json()
   .then(data => readAllItems(data))
  }).catch((err)=> console.error(`${err}`));
}

 function readAllItems(todo) {
  let toDoList = document.getElementById("tasks");
  for(let i = 0; i < todo.length; i++) {
  let li = document.createElement("li");

  let label = document.createElement("label")
  label.textContent = todo[i].description;
  li.appendChild (label);

  let editInput = document.createElement("input")
  editInput.setAttribute("type", "textbox");
  editInput.id = "textbox" + todo[i].id
  li.appendChild(editInput);


  let editButton = document.createElement("button");
  editButton.textContent = "Edit";
  editButton.setAttribute("class", "edit");
  editButton.addEventListener("click",() => editMethod (todo[i].id));
  li.appendChild(editButton);
 

  let deleteButton = document.createElement("button");
  deleteButton.textContent = "Delete";
  deleteButton.setAttribute("class", "delete");
  deleteButton.addEventListener("click",() => deleteMethod (todo[i].id));
 li.appendChild(deleteButton);

 
  toDoList.appendChild(li);
  }
}

 
  const renderToDo = (todo, output) => {
    const newToDo = document.createElement('div');
    
    const description = document.createElement('p');
    description.innerText = `Todo: ${description}`;
    newToDo.appendChild(todo);
    
    output.appendChild(newToDo);
  }

//CREATE

  const create = () => {
let description = document.querySelector("#description").value;
let priority = document.querySelector("#priority").value;

const myobject = 
{
  "description": description, 
  "priority": priority
}

fetch("http://localhost:8080/items/createItem", { 
    method: "post", 
    headers: {
      "Content-type": "application/json; charset=UTF-8"
    },
    body: JSON.stringify(myobject)
  })
  .then(res => res.json())
  .then((data) => console.log(`Request succeeded with JSON response ${data}`))
  .catch((error) => console.log(`Request failed ${error}`));
}

//DELETE

const deleteMethod = (id) => {
fetch("http://localhost:8080/items/DeleteItem/"+ id, {  
    method: 'delete'  
   }).then((data) => {
    console.log(`Request succeeded with JSON response ${data}`);
  }).catch((error) => {
  
  });
}

//UPDATE
const editMethod = (id) => {
  let editInput = document.querySelector("input#textbox" + id).value;

console.dir(editInput)
  const editobject = 
  {
    "id":id,
    "priority": "very boring ",
        "description" : editInput
    
  }
  console.log(editobject)
fetch("http://localhost:8080/items/update/"+ id, {
    method: 'put', 
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(editobject)
  }).then((data) => {
    readmethod();
    console.log(`Request succeeded with JSON response ${data}`);
}).catch((error) => console.log(`Request failed ${error}`));

}





var taskInput = document.getElementById("new-task");
var addButton = document.getElementsByTagName("button")[0];
var incTaskHolder = document.getElementById("tasks");


//New Task List Item
var createNewTaskElement = function(taskString){
    var listItem = document.createElement("li");
   //input (checkbox)
    var checkBox = document.createElement("input"); //checkbox
    //label
    var label = document.createElement("label");
    //input(text)
    var editInput = document.createElement("input");
    //button.edit
    var editButton = document.createElement("button");
    //button.delete
    var deleteButton = document.createElement("button");
    //Each element needs modifying
    checkBox.type = "checkbox";
    editInput.type = "text";
    editButton.innerText = "Edit";
    editButton.className = "edit";
    deleteButton.innerText = "Delete";
    //Each element needs appending
    listItem.appendChild(checkBox); 
    listItem.appendChild(label); 
    listItem.appendChild(editInput); 
    listItem.appendChild(editButton); 
    listItem.appendChild(deleteButton); 

    return listItem
}




//Edit an existing task
var editTask = function(){
    console.log("Edit task...");


    var listItem = this.parentNode;
    var editInput = listItem.querySelector("input[type=text]");
    var label = listItem.querySelector("label");

    var containsClass = listItem.classList.contains("editMode");


        //if the class pf parent is .editmode
    if (containsClass){
      //label text become the input's value  
      label.innerText = editInput.value;

    } else {
        //switch to .editmode
        //input value becomes the label's text
      editInput.value = label.innerText;
    }

    listItem.classList.toggle("editMode"); //toggle .editmode on the parent

}

//Deleting an existing task
var deleteTask = function(){
  console.log("Delete task...");
  //removing parent list item from the ul
  var listItem = this.parentNode;
  var ul = listItem.parentNode;
  ul.removeChild(listItem);

}




//mark a task as complete
var taskComp = function(){
  console.log("Completed task...");
     //when the checkbox is checked
      //append the task list itm to the #completedtasks 
   var listItem = this.parentNode;
  compTaskHolder.appendChild(listItem);
    bindTaskEvents(listItem, taskInc);

}


var bindTaskEvents = function(taskListItem, checkBoxEventHandler){
  console.log("bind list item events...");
  //select li's children
  var checkBox = taskListItem.querySelector("input[type=checkbox]");
  var editButton = taskListItem.querySelector("button.edit");
  var deleteButton = taskListItem.querySelector("button.delete");

  //bind edittask to edit button
  editButton.onclick = editTask;
    //bind deltask to del button
  deleteButton.onclick = deleteTask;
    //bind CBEH to check box
  checkBox.onchange = checkBoxEventHandler;

 }
readmethod();