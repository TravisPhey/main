//@@author vmlimshimin
package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.ui.ThemeRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.RecentlyDeletedQueue;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;

/**
 * Selects a theme based on the index provided by the user, which can be referred from the themes list.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";
    public static final String COMMAND_ALIAS = "ct";

    public static final String MESSAGE_THEME_CHANGE_SUCCESS = "Theme Changed";


    @Override
    public CommandResult execute() {

        String newTheme = (theme.equals("DarkTheme.css")) ? "LightTheme.css" : "DarkTheme.css";

        EventsCenter.getInstance().post(new ThemeRequestEvent(newTheme));

        return new CommandResult(String.format(MESSAGE_THEME_CHANGE_SUCCESS, newTheme));

    }


    @Override
    public void setData(Model model, CommandHistory history, UndoRedoStack undoRedoStack,
                        RecentlyDeletedQueue queue, String theme) {
        this.theme = theme;
    }
}
