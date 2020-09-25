# User Guide

## Features 
1. Add Todo item
1. Add Deadline item
1. Add Event item
1. Print list of Tasks
1. Delete an task item
1. Save tasks to txt file
1. Find tasks with keyword
1. Find tasks on date
1. Exit program

### Feature 1 
Description of feature.
1. Add Todo item
   1. Adds a task with a simple description. 
1. Add Deadline item
   1. Adds a task with a due date and time.
1. Add Event item
   1. Adds a task with a due date and time.
1. Print list of Tasks
   1. Prints out all current tasks.
1. Mark a task as done
   1. Mark a task from the current list ot tasks as done.
1. Delete an task item
   1. Delete a task from the current list of tasks.
1. Save tasks to txt file
   1. Save tasks into a txt file.
1. Find tasks with keyword
   1. Based on a keyword you input, the program searches for tasks containing that keyword.
1. Find tasks on date
   1. Based on a date you input, the program searches for tasks that fall on that date.
1. Exit program
   1. Closes the program.

## Usage
1. Add Todo item - "todo {description}"
1. Add Deadline item - "deadline {description} /by {dd/mm/yy} {hr:mn}"
1. Add Event item - "event {description} /at {dd/mm/yy} {hr:mn)}"
1. Print list of Tasks - "list"
1. Mark a task as done - "done {task number}"
1. Delete an task item - "delete {task number}"
1. Save tasks to txt file - "save"
1. Find tasks with keyword - "find {keyword/phrase}"
1. Find tasks on date - "occur {dd/mm/yy}"
1. Exit program - "bye"

### `todo` - Add Todo item

Example of usage: 

`todo homework`

Expected outcome:

____________________________________________________________

 Got it. I've added this task:

   [T][0] homework

 Now you have 1 tasks in the list.

____________________________________________________________

### `deadline` - Add Deadline item

Example of usage: 

`deadline homework /by 9/9/2020 09:00`

Expected outcome:

____________________________________________________________

 Got it. I've added this task:

   [D][0] homework (by: Sep 9 2020 09:00)

 Now you have 1 tasks in the list.

____________________________________________________________

### `event` - Add Event item

Example of usage: 

`event party /at 10/12/2020 20:00`

Expected outcome:

____________________________________________________________

 Got it. I've added this task:

   [E][0] party (at: Dec 10 2020 20:00)

 Now you have 1 tasks in the list.

____________________________________________________________


### `list` - Print list of Tasks

Example of usage: 

`list`

Expected outcome:

____________________________________________________________
 Here are the tasks in your list:

 1.[D][0] homework (by: Sep 9 2020 09:00)

 2.[E][0] party (at: Dec 10 2020 20:00)

____________________________________________________________

### `done` - Mark a task as done

Example of usage: 

`done 1`

Expected outcome:

____________________________________________________________
 Nice! I've marked this task as done: 

   [T][1] homework

____________________________________________________________

### `delete` - Delete an task item

Example of usage: 

`delete 1`

Expected outcome:

____________________________________________________________
 Noted. I've removed this task: 

   [D][0] homework (by: Sep 9 2020 09:00)

 Now you have 0 tasks in the list.

____________________________________________________________

### `save` - Save tasks to txt file

Example of usage: 

`save`

Expected outcome:

____________________________________________________________
 Nice! I have saved your list.

____________________________________________________________

### `find` - Find tasks with keyword

Example of usage: 

`find homework`

Expected outcome:

____________________________________________________________
 Here are the matching tasks in your list:

 1.[T][0] homework

 2.[D][0] homework (by: Sep 9 2020 09:00)

____________________________________________________________

### `occur` - Find tasks on date

Example of usage: 

`occur 9/9/2020`

Expected outcome:

____________________________________________________________
 Tasks due on: 2020-09-09

 1.[D][0] homework (by: Sep 9 2020 09:00)

____________________________________________________________

### `bye` - Exit program

Example of usage: 

`bye`

Expected outcome:

____________________________________________________________
 Bye. Hope to see you again soon!

____________________________________________________________


