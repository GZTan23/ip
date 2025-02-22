package bane.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import bane.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void markTaskTest_validIndex_boolean() {
        DeadlineStub stub = new DeadlineStub("return books", false);
        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList.add(stub);
        TaskList taskList = new TaskList(arrayList);
        taskList.markTask(1);
        assertTrue(taskList.getTaskList().get(0).isTaskDone());
    }

    @Test
    public void unmarkTaskTest_validIndex_boolean() {
        DeadlineStub stub = new DeadlineStub("return books", true);
        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList.add(stub);
        TaskList taskList = new TaskList(arrayList);
        taskList.unmarkTask(1);
        assertFalse(taskList.getTaskList().get(0).isTaskDone());
    }

    @Test
    public void isEmptyTest_nonEmptyArray_false() {
        DeadlineStub stub = new DeadlineStub("return books", true);
        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList.add(stub);
        TaskList taskList = new TaskList(arrayList);
        assertFalse(taskList.isEmpty());

    }

    @Test
    public void isEmptyTest_emptyArray_true() {
        ArrayList<Task> arrayList = new ArrayList<Task>();
        TaskList taskList = new TaskList(arrayList);
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void findTask_validTaskName_successReply() {
        DeadlineStub stub1 = new DeadlineStub("return books", true);
        DeadlineStub stub2 = new DeadlineStub("return clothes", false);
        DeadlineStub stub3 = new DeadlineStub("return pen", true);
        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList.add(stub1);
        arrayList.add(stub2);
        arrayList.add(stub3);
        TaskList taskList = new TaskList(arrayList);
        String output1 = taskList.findTask("books");
        String expected1 = Ui.replyToFind("success") + """
                1. [D] [X] [ ] return books
                """;
        String output2 = taskList.findTask("re");
        String expected2 = Ui.replyToFind("success") + """
                1. [D] [X] [ ] return books
                2. [D] [ ] [ ] return clothes
                3. [D] [X] [ ] return pen
                """;
        assertEquals(expected1, output1);
        assertEquals(expected2, output2);
    }

    @Test
    public void findTask_taskNameNotInArray_unableToFindReply() {
        DeadlineStub stub1 = new DeadlineStub("return books", true);
        DeadlineStub stub2 = new DeadlineStub("return clothes", false);
        DeadlineStub stub3 = new DeadlineStub("return pen", true);
        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList.add(stub1);
        arrayList.add(stub2);
        arrayList.add(stub3);
        TaskList taskList = new TaskList(arrayList);
        String output1 = taskList.findTask("bye");
        String output2 = taskList.findTask("board");
        String output3 = taskList.findTask("hi");
        String expected = Ui.replyToFind("not_found");
        assertEquals(expected, output1);
        assertEquals(expected, output2);
        assertEquals(expected, output3);
    }

}
