# Duke Program
## Duke User Guide

### Table of contents
* [Quick Start](#quick-start)  
* [Features](#features)
  * [Add Todo item](#todo---add-a-todo-item)
  * [Add Deadline item](#deadline---add-a-deadline-item)
  * [Add Event item](#event---add-a-event-item)
  * [Print list of Tasks](#list---print-list-of-tasks)
  * [Delete a task item](#delete---delete-an-task-item)
  * [Save tasks to txt file](#save---save-tasks-to-txt-file)
  * [Find tasks with keyword](#find---find-tasks-with-keyword)
  * [Find tasks on date](#occur---find-tasks-on-date)
  * [Exit program](#bye---exit-program)
* [FAQ](#frequently-asked-questions)
* [Command Summary](#command-summary)

### Quick Start
1. Ensure that **Java 11.0.8** is installed on your Computer.
2. Download the executable jar file [here](https://github.com/TYS0n1/ip/releases).
3. Copy the directory address of the folder the jar file is downloaded to on your computer.
4. Start command prompt and go to the directory of the jar file by typing <br>
`cd ADDRESS_COPIED_IN_STEP_3`.
5. Start running the jar file by typing `java -jar ip.jar`.
6. You should see txt file **taskList.txt** appears in the folder. If it does not, type `bye` and repeat step 4 and 5.

### Features 

>Note on formatting of commands 
>* Commands written in **UPPERCASE** are parameters to be supplied by you.<br>
>eg. `todo DESCRIPTION`, `DESCRIPTION` is the information about the todo task supplied by you. 
>You need not add type the parameter in uppercase. eg `todo homework` is alright.
>
>* When a date parameter is to be supplied by you, **DATE**, 
>your input can be in the either dd/mm/yy format or dd-mm-yy format. <br>
>eg. `event abc /at 06/09/2011 00:00` or `event abc /at 06-09-2011 00:00` <br>
>Also, your date input need not be padded with zeros <br>
>eg. an event entry on the 6th of september 900 AD
>can be entered as: <br>`event abc /at 6/9/900 00:00` or `event abc /at 06/09/0900 00:00` without errors.
>
>* When a time parameter is to be supplied by you, **TIME**,
>your input needs to follow hh:mn format and be in terms 24 hours.
>eg. an event entry to occur at 1.35pm will be entered as: <br>
>`event abc /at 06/09/2011 13:35`.
>
>* When a number parameter is to be supplied by you, **NUMBER**,
>your input has to be a number that lies in your task list. <br>
>eg. your have 4 tasks in your list. The valid numbers you can input are from 1 to 4. 

#### `todo` - Add a Todo item

Adds a task with a simple description. <br>
Command: `todo DESCRIPTION`

Example of usage: 

`todo homework`

Expected outcome:

------------------------------------------------------------<br>

 Got it. I've added this task:

   [T][0] homework

 Now you have 1 tasks in the list.

------------------------------------------------------------<br>

#### `deadline` - Add a Deadline item

Adds a task with a due date and time. <br>
Command: `deadline DESCRIPTION /by DATE TIME`

Example of usage: 

`deadline homework /by 9/9/2020 09:00`

Expected outcome:

------------------------------------------------------------<br>

 Got it. I've added this task:

   [D][0] homework (by: Sep 9 2020 09:00)

 Now you have 1 tasks in the list.

------------------------------------------------------------<br>

#### `event` - Add a Event item

Adds a task with a due date and time. <br>
Command : `event DESCRIPTION /at DATE TIME`

Example of usage: 

`event party /at 10/12/2020 20:00`

Expected outcome:

------------------------------------------------------------<br>

 Got it. I've added this task:

   [E][0] party (at: Dec 10 2020 20:00)

 Now you have 1 tasks in the list.

------------------------------------------------------------<br>


#### `list` - Print list of Tasks

Prints out all current tasks. <br>
Command: `list`

Example of usage: 

`list`

Expected outcome:

------------------------------------------------------------<br>
 Here are the tasks in your list:

 1.[D][0] homework (by: Sep 9 2020 09:00)

 2.[E][0] party (at: Dec 10 2020 20:00)

------------------------------------------------------------<br>

#### `done` - Mark a task as done

Mark a task from the current list ot tasks as done. <br>
Command: `done NUMBER`

Example of usage: 

`done 1`

Expected outcome:

------------------------------------------------------------<br>
 Nice! I've marked this task as done: 

   [T][1] homework

------------------------------------------------------------<br>

#### `delete` - Delete an task item

Delete a task from the current list of tasks. <br>
Command: `delete NUMBER`

Example of usage: 

`delete 1`

Expected outcome:

------------------------------------------------------------<br>
 Noted. I've removed this task: 

   [D][0] homework (by: Sep 9 2020 09:00)

 Now you have 0 tasks in the list.

------------------------------------------------------------<br>

#### `save` - Save tasks to txt file

Save tasks into a txt file. <br>
**Note:** The program automatically saves changes. <br>
Command: `save`

Example of usage: 

`save`

Expected outcome:

------------------------------------------------------------<br>
 Nice! I have saved your list.

------------------------------------------------------------<br>

#### `find` - Find tasks with keyword

Based on a keyword you input, the program searches for tasks containing that keyword. <br>
Command: `find PHRASE`

Example of usage: 

`find homework`

Expected outcome:

------------------------------------------------------------<br>
 Here are the matching tasks in your list:

 1.[T][0] homework

 2.[D][0] homework (by: Sep 9 2020 09:00)

------------------------------------------------------------<br>

#### `occur` - Find tasks on date

Based on a date you input, the program searches for tasks that fall on that date. <br>
Command: `occur DATE`

Example of usage: 

`occur 9/9/2020`

Expected outcome:

------------------------------------------------------------<br>
 Tasks due on: 2020-09-09

 1.[D][0] homework (by: Sep 9 2020 09:00)

------------------------------------------------------------<br>

#### `bye` - Exit program

Closes the program. <br></br>
Command: `bye`

Example of usage: 

`bye`

Expected outcome:

------------------------------------------------------------<br>
 Bye. Hope to see you again soon!"
 
------------------------------------------------------------<br>


### Frequently Asked Questions
**Q:** I can't find the txt file containing my saved tasks. <br>
**A:** You did not run the `cd ADDRESS_OF_JAR_FILE` command in the [Quick Start](#quick-start) section. <br>
Use the search bar on your computer to find a txt file called **taskList** to retrieve your saved tasks.


### Command Summary
Feature | Command format
------------ | -------------
`todo` | `todo DESCRIPTION`
`deadline` | `deadline DESCRIPTION /by DATE TIME`
`event` | `event DESCRIPTION /at DATE TIME`
`list` | `list`
`done` | `done NUMBER`
`delete` | `delete NUMBER`
`save` | `save`
`find` | `find PHRASE`
`occur` | `occur DATE`
`bye` | `bye`
