All source files are located in src.
Jar file is located in mods/artifacts/BattleshipGame_jar.
To run Jar file, please edit JavaFX path in start.bat and launch start.bat from Command Prompt.
There were also some Bat files for starting with modes - "Server", "Client", "Solo".

All the test cases were associated with only hse.edu.battleship.core package, which presents all the classes that were
used in HW-1. UI tests were not created as their result can be seen only while all the system is working. So you can
see it by just lunching and playing this game.

As for the framework for GUI the JavaFX was used with FXML files created by SceneBuilder. All windows have their
Controller classes.

After the launch there is a splash window which will be open until closing by button. To start a game please choose
"Start new battle, arr!", and "Pathetic run, loser!" for exit. Then you will need to choose ships selection mode:
1. if you want to get some random board with ships already allocated - "Random"
2. if you want to manually select ships - "Custom"

At the start board is filled with cyan cells, when you shoot at the cell, it changes its colour. If it's empty it gets
blue color, if the ship is damaged, it's get red and it's get green if ship is sunk.

You can type coordinates separated by space in textfield and click to button "Shoot". If the coordinates were not
correct, message will be shown. Also you can click to the cell using mouse, the cell will get focused and you will see
dark borders around that cell. If the game is over, the message will be shown with the score.

As for ship selection window, you need to place all ships and click to Ok button, if you click cancel, random ocean
field will be used further. As you move pointer, the preview of placeable ship will be shown. You can choose orientation
 "Vertical" or "Horizontal" and you can revert your last ship selection by "Revert" and you can clear field by "Clear".
You can navigate in ocean using moving pointer keys. For more comfortable access there were some shortcuts to this
buttons: "Vertical" - V, "Horizontal" - H, "Revert" - R, "Clear" - C.

When the application was closed or the quit button was pressed, a dialog with only OK option will be shown, which would
close application. By exiting application, all threads execute their overridden interrupt() and all connections were
closed.

As for getting all messages to "log field", custom class was used as some bridge between field and System.out stream.