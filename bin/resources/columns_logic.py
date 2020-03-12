# columns_logic.py
# Amanda Lin 43359846
# ICS 32 Project 4

class InvalidMoveError(Exception):
    '''Raised whenever an invalid move is made.'''
    pass

class GameOverError(Exception):
    '''Raised whenever an attempt is made to make a move after the game is already over'''
    pass

class GameState:
    def __init__(self, rows: int, columns: int):
        self.rows = rows
        self.columns = columns
        self.NONE = 0
        self.faller = None
        self.changed = False
        self.is_matched = False
        self.matched = []
        self.board = self.new_game_board()

    def new_game_board(self) -> [[int]]:
        '''Creates a 2d list representing the Columns game board.'''
        board = []
        for row in range(self.rows):
            board.append([])
            for column in range(self.columns):
                board[-1].append(self.NONE)
        return board

    def initialize_faller(self, column: int, colors: [str]) -> None:
        '''Creates a new faller and places the bottommost jewel on the corresponding column of the board.'''
        self.faller = Faller(column-1, colors)
        self.board[0][column - 1] = self.faller.bottom_color

    def drop_faller(self) -> None:
        '''Drops the faller down the board 1 row at a time until it cannot go any further.'''
        if self.faller_exists():
            self.require_valid_column_number(self.faller.column)
            self.require_valid_row_number(self.faller.row)
            self.require_game_not_over()

            if self.is_falling():
                self.faller.row += 1
                self.board[self.faller.row][self.faller.column] = self.faller.bottom_color
                self.board[self.faller.row-1][self.faller.column] = self.faller.middle_color
                if self.is_valid_row(self.faller.row-2):
                    self.board[self.faller.row-2][self.faller.column] = self.faller.top_color
                if self.is_valid_row(self.faller.row-3):
                    self.board[self.faller.row - 3][self.faller.column] = self.NONE
            elif self.has_landed():
                self.freeze_faller()
        else:
            pass

    def rotate_faller(self) -> None:
        '''Rotates the faller if the faller exists.'''
        if self.faller_exists():
            self.require_valid_column_number(self.faller.column)
            self.require_valid_row_number(self.faller.row)
            self.require_game_not_over()

            temp_color = self.board[self.faller.row][self.faller.column]
            self.faller.bottom_color = self.board[self.faller.row][self.faller.column] = \
                self.board[self.faller.row-1][self.faller.column]
            self.faller.middle_color = self.board[self.faller.row - 1][self.faller.column] = \
                self.board[self.faller.row - 2][self.faller.column]
            self.faller.top_color = self.board[self.faller.row - 2][self.faller.column] = temp_color

            if not self.is_valid_row(self.faller.row-2):
                raise InvalidMoveError()
        else:
            pass

    def freeze_faller(self) -> None:
        '''Freezes the faller by adding its contents to the board and deleting its faller status.'''
        if self.faller_exists():
            if self.has_landed() and not self.has_changed():
                self.add_faller_to_board()
                self.faller = None
            if self.has_landed() and self.has_changed():
                self.add_faller_to_board()
                self.faller = None

    def add_faller_to_board(self) -> None:
        '''Adds the faller's contents to the board.'''
        self.board[self.faller.row][self.faller.column] = self.faller.bottom_color
        self.board[self.faller.row-1][self.faller.column] = self.faller.middle_color
        self.board[self.faller.row-2][self.faller.column] = self.faller.top_color

    def empty_moved_cell(self, column: int, color: str) -> None:
        '''Empties the cell of which the player is trying to move right or left.'''
        if self.board[self.faller.row][column] == color:
            self.board[self.faller.row][column] = self.NONE
            self.board[self.faller.row-1][column] = self.NONE
            self.board[self.faller.row-2][column] = self.NONE

    def move_faller_left(self) -> None:
        '''Moves the faller one column to the left.'''
        if self.faller_exists():
            self.require_valid_column_number(self.faller.column)
            self.require_valid_row_number(self.faller.row)
            self.require_game_not_over()

            if self.is_valid_column(self.faller.column-1):
                if self.board[self.faller.row][self.faller.column-1] == self.NONE:
                    self.board[self.faller.row][self.faller.column-1] = self.faller.bottom_color
                    self.empty_moved_cell(self.faller.column, self.faller.bottom_color)
                    self.board[self.faller.row-1][self.faller.column-1] = self.faller.middle_color
                    self.empty_moved_cell(self.faller.column, self.faller.middle_color)
                    self.board[self.faller.row-2][self.faller.column-1] = self.faller.top_color
                    self.empty_moved_cell(self.faller.column, self.faller.top_color)
                    self.faller.column -= 1
                    if self.has_landed():
                        self.changed = True
                    if not self.is_valid_row(self.faller.row - 2):
                        raise InvalidMoveError()
                else:
                    pass
        else:
            pass

    def move_faller_right(self) -> None:
        '''Moves the faller one column to the right.'''
        if self.faller_exists():
            self.require_valid_column_number(self.faller.column)
            self.require_valid_row_number(self.faller.row)
            self.require_game_not_over()

            if self.is_valid_column(self.faller.column+1):
                if self.board[self.faller.row][self.faller.column+1] == self.NONE:
                    self.board[self.faller.row][self.faller.column+1] = self.faller.bottom_color
                    self.empty_moved_cell(self.faller.column, self.faller.bottom_color)
                    self.board[self.faller.row-1][self.faller.column+1] = self.faller.middle_color
                    self.empty_moved_cell(self.faller.column, self.faller.middle_color)
                    self.board[self.faller.row-2][self.faller.column+1] = self.faller.top_color
                    self.empty_moved_cell(self.faller.column, self.faller.top_color)
                    self.faller.column += 1
                    if self.has_landed():
                        self.changed = True
                    if not self.is_valid_row(self.faller.row - 2):
                        raise InvalidMoveError()
                else:
                    pass
        else:
            pass

    def move_pieces_down(self) -> None:
        '''Moves existing cell pieces down when there is nothing under it.'''
        for row in range(self.rows):
            for column in range(self.columns):
                if self.is_valid_row(row + 1):
                    if self.board[row + 1][column] == self.NONE:
                        self.board[row + 1][column] = self.board[row][column]
                        self.board[row][column] = self.NONE
                        if self.is_valid_row(row - 1):
                            if self.board[row-1][column] != self.NONE:
                                self.board[row][column] = self.board[row-1][column]
                                self.board[row-1][column] = self.NONE

    def mark_matched(self) -> None:
        '''Marks the matched cells as matched'''
        self.handle_right_match()
        self.handle_right_diagonal_match()
        self.handle_down_match()
        self.handle_left_diagonal_match()

    def handle_right_match(self):
        '''Searches for matches going in the right direction.'''
        for row in range(self.rows):
            for col in range(self.columns):
                temp_row = row
                temp_col = col
                if self.match_found(row, col, 0, 1) >= 3:
                    for i in range(self.match_found(row, col, 0, 1)):
                        self.matched.append([temp_row, temp_col])
                        temp_row += 0
                        temp_col += 1
                    temp_row = row
                    temp_col = col
                    self.is_matched = True

    def handle_right_diagonal_match(self):
        '''Searches for matches going in the right diagonal direction.'''
        for row in range(self.rows):
            for col in range(self.columns):
                temp_row = row
                temp_col = col
                if self.match_found(row, col, 1, 1) >= 3:
                    for i in range(self.match_found(row, col, 1, 1)):
                        self.matched.append([temp_row, temp_col])
                        temp_row += 1
                        temp_col += 1
                    temp_row = row
                    temp_col = col
                    self.is_matched = True

    def handle_down_match(self):
        '''Searches for matches going downwards.'''
        for row in range(self.rows):
            for col in range(self.columns):
                temp_row = row
                temp_col = col
                if self.match_found(row, col, 1, 0) >= 3:
                    for i in range(self.match_found(row, col, 1, 0)):
                        self.matched.append([temp_row, temp_col])
                        temp_row += 1
                        temp_col += 0
                    temp_row = row
                    temp_col = col
                    self.is_matched = True

    def handle_left_diagonal_match(self):
        '''Searches for matches going in the left diagonal direction.'''
        for row in range(self.rows):
            for col in range(self.columns):
                temp_row = row
                temp_col = col
                if self.match_found(row, col, 1, -1) >= 3:
                    for i in range(self.match_found(row, col, 1, -1)):
                        self.matched.append([temp_row, temp_col])
                        temp_row += 1
                        temp_col += -1
                    temp_row = row
                    temp_col = col
                    self.is_matched = True

    def delete_matched(self) -> None:
        '''Deletes the marked matches from the board.'''
        if self.has_match():
            for match in self.matched:
                m_row = match[0]
                m_col = match[1]
                for row in range(self.rows):
                    for column in range(self.columns):
                        if m_row == row and m_col == column:
                            self.board[row][column] = self.NONE
                        self.move_pieces_down()
                        self.is_matched = False
                        self.freeze_faller()
                        self.matched = []

    def match_found(self, row: int, col: int, rowdelta: int, coldelta: int) -> int:
        '''Searches for cells eligible to be matches'''
        start_cell = self.board[row][col]
        counter = 0
        if start_cell == self.NONE:
            return counter
        else:
            while True:
                if not self.is_valid_row(row + rowdelta * counter) \
                        or not self.is_valid_column(col + coldelta * counter) \
                        or self.board[row + rowdelta * counter][col + coldelta * counter] != start_cell \
                        or self.is_falling():
                    break
                else:
                    counter += 1
        return counter

    def handle_game_over(self) -> None:
        '''Freezes the faller once the game is over.'''
        if self.is_game_over():
            self.freeze_faller()

    def is_game_over(self) -> bool:
        '''Returns true when the faller freezes without all three of its jewels being visible in the field.'''
        if self.faller_exists() and self.has_landed():
            if self.is_valid_row(self.faller.row+1):
                return self.board[self.faller.row+1][self.faller.column] != self.NONE \
                        and not self.is_valid_row(self.faller.row-2)

    def faller_exists(self) -> bool:
        '''Returns true when a faller exists and is currently being handled on the board.'''
        return self.faller is not None

    def has_changed(self) -> bool:
        '''Returns true when a faller has changed its original state.'''
        return self.changed

    def is_falling(self) -> bool:
        '''Returns true if the faller is falling and nothing is below it.'''

        if self.faller_exists():
            if self.is_valid_row(self.faller.row + 1):
                return self.board[-1][self.faller.column] == self.NONE or \
                       self.board[self.faller.row + 1][self.faller.column] == self.NONE

    def has_landed(self) -> bool:
        '''Returns true when the faller lands and it cannot drop any further.'''
        if not self.has_match():
            return not self.is_falling()

    def has_match(self) -> bool:
        '''Returns true when there is a valid match found on the board.'''
        return self.is_matched

    def require_game_not_over(self) -> None:
        '''Raises a GameOverError if the given game state represents a situation where the game is over"
        '''
        if self.is_game_over():
            raise GameOverError()

    def require_valid_row_number(self, row: int) -> None:
        '''Raises a ValueError if its parameter is not a valid row number'''
        if type(row) != int or not self.is_valid_row(row):
            raise ValueError('column_number must be int between 0 and {}'.format(self.rows))

    def require_valid_column_number(self, column: int) -> None:
        '''Raises a ValueError if its parameter is not a valid column number'''
        if type(column) != int or not self.is_valid_column(column):
            raise ValueError('column_number must be int between 0 and {}'.format(self.columns))

    def is_valid_column(self, column: int) -> bool:
        '''Checks if a column is a valid one that is accounted for on the board.'''
        return 0 <= column < self.columns

    def is_valid_row(self, row: int) -> bool:
        '''Checks if a row is a valid one that is accounted for on the board.'''
        return 0 <= row < self.rows

class Faller:
    def __init__(self, column: int, colors: [str]):
        self.bottom_color = colors[2]
        self.middle_color = colors[1]
        self.top_color = colors[0]
        self.column = column
        self.row = 0
        self.colors = [self.bottom_color, self.middle_color, self.top_color]