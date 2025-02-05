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
        assertTrue(taskList.getList().get(0).isTaskDone());
    }

    @Test
    public void unmarkTaskTest_validIndex_boolean() {
        DeadlineStub stub = new DeadlineStub("return books", true);
        ArrayList<Task> arrayList = new ArrayList<Task>();
        arrayList.add(stub);
        TaskList taskList = new TaskList(arrayList);
        taskList.unmarkTask(1);
        assertFalse(taskList.getList().get(0).isTaskDone());
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

}
