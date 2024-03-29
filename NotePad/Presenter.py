from dataclasses import dataclass
import LibOfClass
import ViewClass


@dataclass
class Presenter:
    command: list[str]
    work_notepad: LibOfClass.NotePad
    '''
    void add_new_note(title: str, text: str),
    int count_notes(),
    void delete_note(id_note: int),
    void correct_note_title(id_note: int),
    void correct_note_text(id_note: int)
    SingleNote pop_note(id_note: int), 
    SingleNote read_note(id_note: int),
    
    '''

    def __init__(self, args, work_notepad):
        self.command = args
        self.work_notepad = work_notepad
        if len(args) == 1:
            raise Exception("Команда не введена. Для справки введите --help")
        if len(args) > 2:
            raise Exception("Слишком много аргументов. Для справки введите --help")

    def get_command(self):
        my_note = self.work_notepad
        NewConsole = ViewClass.ViewConsoleView(100)
        if self.command[1] == '--test':
            my_note = LibOfClass.return_test_notePad()
            NewConsole.console_test()
            NewConsole.console_full_note(my_note)
            my_note.read_note(10)
        elif self.command[1] == '--help':
            print('''--help  / справка 
--test  / запуск программы в режиме автотеста
--list  / чтение всех заметок 
--add   / создание новой заметки
--del   / удаление заметки по ключу
--pop   / удаление заметки по ключу с чтением
--rbi   / чтение заметки по ключу
--rbd   / чтение заметки по дате
--cttl   / редактировать название заметки
--ctxt  / редактировать содержание заметки
                            ''')
        elif self.command[1] == '--add':
            t = NewConsole.add_dialog()
            my_note.add_new_note(t[0], t[1])
        elif self.command[1] == '--del':
            t_id = NewConsole.del_dialog()
            my_note.delete_note(t_id)
        elif self.command[1] == '--pop':
            t_id = NewConsole.del_dialog()
            NewConsole.console_single_print(my_note.read_note(t_id), t_id.__str__())
            my_note.delete_note(t_id)
        elif self.command[1] == '--list':
            NewConsole.console_full_note(my_note)
        elif self.command[1] == '--rbi':
            t_id = NewConsole.read_dialog()
            NewConsole.console_single_print(my_note.read_note(t_id), t_id.__str__())
        elif self.command[1] == '--rbd':
            t_date = NewConsole.read_dialog_by_date()
            t_id_list = my_note.read_id_by_date(t_date.__str__())
            for i_id in t_id_list:
                NewConsole.console_single_print(my_note.read_note(i_id), i_id.__str__())
        elif self.command[1] == '--cttl':
            tuple_data = NewConsole.correct_dialog()
            my_note.correct_note_title(tuple_data[0], tuple_data[1])
        elif self.command[1] == '--ctxt':
            tuple_data = NewConsole.correct_dialog()
            my_note.correct_note_title(tuple_data[0], tuple_data[1])


        
