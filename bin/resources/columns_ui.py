# columns_ui.py
# Amanda Lin 43359846
# ICS 32 Project 4

import columns_logic

def print_board(game_state: columns_logic.GameState) -> str:
    '''Prints out the Columns game board.'''
    for row in range(game_state.rows):
        print("|", end='')
        for column in range(game_state.columns):
            num_spaces = 3 * game_state.columns
            if not game_state.has_match():
                print_unmatched_cells(game_state, row, column)
            elif game_state.has_match():
                print_matched_cells(game_state, row, column)
        print("|")
    print(' ' + '-' * num_spaces + ' ')

def print_unmatched_cells(game_state: columns_logic.GameState, row: int, column: int) -> str:
    '''Prints the unmatched cells of the game board with specific characters.'''
    chars = '   '
    if game_state.board[row][column] == game_state.NONE:
        print(chars, end='')
    elif game_state.board[row][column] != game_state.NONE:
        cell = game_state.board[row][column]
        if game_state.faller_exists():
            if column == game_state.faller.column and row <= game_state.faller.row:
                if game_state.is_falling():
                    chars = '[]'
                elif game_state.has_landed():
                    chars = '||'
        print(chars[0] + cell + chars[1], end='')

def print_matched_cells(game_state: columns_logic.GameState, row: int, column: int) -> str:
    '''Prints out matched cells of the game board with specific characters.'''
    if game_state.board[row][column] == get_matched_cell(game_state):
        cell = game_state.board[row][column]
        print('*' + cell + '*', end='')
    else:
        print_unmatched_cells(game_state, row, column)

def get_matched_cell(game_state: columns_logic.GameState) -> str:
    '''Returns the matched cell from the game to compare the board cell with.'''
    if game_state.has_match():
        for match in game_state.matched:
            cell = game_state.board[match[0]][match[1]]
    return cell

def read_num_rows() -> int:
    '''Reads a line of input specifying the number of rows in the field.'''
    row_input = int(input().strip())
    if row_input >= 4:
        return row_input

def read_num_columns() -> int:
    '''Reads a line of input specifying the number of columns in the field.'''
    col_input = int(input().strip())
    if col_input >= 3:
        return col_input

def display_field_type(game_state: columns_logic.GameState) -> None:
    '''Reads the desired field type, empty or contents.'''
    field_input = (input().strip()).upper()
    if field_input == 'EMPTY':
        for row in range(game_state.rows):
            for column in range(game_state.columns):
                game_state.board[row][column] = game_state.NONE
    elif field_input == 'CONTENTS':
        handle_contents(game_state)

def handle_contents(game_state: columns_logic.GameState) -> None:
    '''Places the desired jewels on the board for the content field type.'''
    contents = read_contents(game_state)
    for row in range(game_state.rows):
        for column in range(game_state.columns):
            if contents[row][column] == ' ':
                game_state.board[row][column] = game_state.NONE
            else:
                game_state.board[row][column] = contents[row][column]
    game_state.move_pieces_down()

def read_contents(game_state: columns_logic.GameState) -> list:
    '''Returns a list of the desired jewels for the content field type.'''
    content_list = []
    for row in range(game_state.rows):
        content_str = ''
        row_contents = input().upper()
        if len(row_contents) == game_state.columns:
            content_str += row_contents
            content_list.append(content_str)
        else:
            print('Invalid content input.')
    return content_list

def initiate_game() -> 'game_state':
    '''Initializes the Columns game state and board.'''
    rows = read_num_rows()
    columns = read_num_columns()
    game_state = columns_logic.GameState(rows, columns)
    return game_state

def play_game(game_state: columns_logic.GameState) -> None:
    '''Displays the field and acts on the user commands manipulating the fallers. Ends when game is over.'''
    display_field_type(game_state)
    print_board(game_state)
    while True:
        user_input = input()
        if user_input == '':
            if len(game_state.matched) > 0:
                game_state.delete_matched()
                print_board(game_state)
            else:
                game_state.drop_faller()
                game_state.mark_matched()
                print_board(game_state)

        elif user_input != '':
            command = user_input[0].upper()
            if command == 'F':
                column = int(user_input[2])
                colors = user_input[4:].upper().split()
                game_state.initialize_faller(column, colors)
                print_board(game_state)
            if command == 'R':
                game_state.rotate_faller()
                print_board(game_state)
            if command == '<':
                game_state.move_faller_left()
                print_board(game_state)
            if command == '>':
                game_state.move_faller_right()
                print_board(game_state)
            if command == 'Q':
                break

        if game_state.is_game_over():
            game_state.handle_game_over()
            print_board(game_state)
            print("GAME OVER")
            break

if __name__ == '__main__':
    game_state = initiate_game()
    play_game(game_state)
