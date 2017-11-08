package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.deleteFirstPerson;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.RecentlyDeletedQueue;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RecentlyDeletedCommandTest {
    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();
    private static final UndoRedoStack EMPTY_STACK = new UndoRedoStack();
    private static final String DEFAULT_THEME = new String();
    private static final RecentlyDeletedQueue queue = new RecentlyDeletedQueue();

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final DeleteCommand deleteCommandOne = new DeleteCommand(INDEX_FIRST_PERSON);
    private final DeleteCommand deleteCommandTwo = new DeleteCommand(INDEX_FIRST_PERSON);
    private final RecentlyDeletedCommand recentlyDeletedCommand = new RecentlyDeletedCommand();

    @Before
    public void setUp() {
        deleteCommandOne.setData(model, EMPTY_COMMAND_HISTORY,
                EMPTY_STACK, queue, DEFAULT_THEME);
        deleteCommandTwo.setData(model, EMPTY_COMMAND_HISTORY,
                EMPTY_STACK, queue, DEFAULT_THEME);
        recentlyDeletedCommand.setData(model, EMPTY_COMMAND_HISTORY,
                EMPTY_STACK, queue, DEFAULT_THEME);
    }

    @Test
    public void execute() throws Exception {
        // no persons in RecentlyDeletedQueue
        assertCommandResult(recentlyDeletedCommand, RecentlyDeletedCommand.MESSAGE_NO_RECENTLY_DELETED);

        deleteCommandOne.execute();
        deleteCommandTwo.execute();
        //recentlyDeletedCommand.setData(model, EMPTY_COMMAND_HISTORY,
                //EMPTY_STACK, queue, DEFAULT_THEME);

        // multiple persons in RecentlyDeleted Queue
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        deleteFirstPerson(expectedModel);
        deleteFirstPerson(expectedModel);
        assertCommandSuccess(recentlyDeletedCommand, model, RecentlyDeletedCommand.MESSAGE_SUCCESS, expectedModel);

    }

    /**
     * Asserts that the result message from the execution of {@code recentlyDeletedCommand}
     * equals to {@code expectedMessage}
     */
    private void assertCommandResult(RecentlyDeletedCommand recentlyDeletedCommand, String expectedMessage) {
        assertEquals(expectedMessage, recentlyDeletedCommand.execute().feedbackToUser);
    }
}
