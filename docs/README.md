# Bane User Guide

![UI](/docs/Ui.png)

Have you ever wanted someone to berate you while keeping track of your tasks? 
Well, Bane is the chatbot for you!

> [!WARNING]
> If you are easily offended or angry, I am not responsible or will not 
> be held liable for the damages indirectly or directly caused by use of this chatbot

### Features

<details>
<summary> Add tasks </summary>

## <ins> Add Tasks </ins>

Add tasks you want to keep track to the list \
There are 3 types of tasks, `Todo`, `Deadline`, `Event` 

> [!NOTE]
> Parameters surrounded by `{ }` are optional whilst `[ ]` are mandatory
 
### Todo

Usage: `todo [taskname]` \
Example: `todo return book`

### Deadline

Usage: `deadline [taskname] /by [date] {time}` \
Example: `deadline return book /by 16-1-2001 16:00`,
`deadline homework /by 17-08-2025`

### Event

Usage: `deadline [taskname] /from [date] {time} /to [date] {time}` \
Example: `event return book /from 16-1-2001 16:00 /to 17-1-2001 17:00`

</details>
<details>
<summary>Delete task </summary>

## <ins> Delete task </ins>

Remove unwanted tasks from the task list

Usage: `delete [index of task from list]` \
Example: `delete 2`


</details>
<details>
<summary> Search for tasks </summary>

## <ins> Search for tasks </ins>

You can search for tasks by its task name

Usage: `find [keyword]` \
Example: `find return`

</details> 

<details>
<summary>Set reminders </summary>

## <ins> Set Reminders </ins>

Set reminders that list when the chatbot starts up

Usage: `mark reminder [index of task from list]` \
Example: `mark reminder 2`

</details>

<details>
<summary>Mark/unmark tasks as done </summary>

## <ins> Mark/unmark tasks as done </ins>

Mark tasks as done or unmark them as needed

### Mark

Usage: `mark task [index of task from list]` \
Example: `mark task 2`

### Unmark

Usage: `unmark task [index of task from list]` \
Example: `unmark task 4`

</details>

<details>
<summary>List tasks/reminders </summary>

## <ins>List tasks/reminders </ins>

List tasks or reminders that have been added to the chatbot

### Tasks

Usage: `list tasks`

### Reminders

Usage: `list reminders'

</details>

Once done using the chatbot, you can use `bye` to close the chatbot or just close the window
