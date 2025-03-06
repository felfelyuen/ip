# Harry CLI User Guide

This is a user guide on how to use Harry, your new, slightly deranged, CLI to help(?) you with your task handling.

## **Harry's features**

### `todo` , `deadline` , `event`

Adds a task of that nature into your task list.
`deadline`  requires a "/by" date to be inputted as well.
`event` also requires a "`/from`" and "`/to`" date to be inputted.

Example:
```
deadline do homework /by 2025-05-03 23:59
event go to wedding  /from 03062025 1200 /to 03062025 2200
```

**Note:** there are only a few formats of the inputted date that is accepted:
YYYYMMDD or DDMMYYYY or YYYY-MM-DD or DD-MM-YYYY, and HH:MM or HHMM.
Any permutation of the above formats is accepted.
Example of accepted date format:
`YYYYMMDD HH:MM`

### `list`

Displays the list of tasks to you

### `mark` , `unmark`

Marks a task as completed or incomplete.
Example:
`mark 1`

### `find`

Finds the tasks that contains the inputted keyword
Example:
`find book`

### `delete`

Deletes a task off the tasklist
Example:
`delete 2`

### `bye`

Exits the program

Don't worry about data being saved, it saves automatically every time into a text file, and is loaded up every time you log on to the application!
