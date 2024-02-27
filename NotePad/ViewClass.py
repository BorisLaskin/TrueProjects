from dataclasses import dataclass
import LibOfClass
from datetime import datetime, date


@dataclass()
class ViewConsoleView:
    __console_length: int

    def __init__(self, console_length=100):
        self.__console_length = console_length

    def add_dialog(self):
        title = input("Введите заголовок новой заметки:\t")
        text = input("Введите текст новой заметки. После нажатия клавиши Enter ввод будет закончен:\t")
        print("Создана новая заметка")
        return tuple([title, text])

    def del_dialog(self):
        while True:
            try:
                del_id = int(input("Введите индекс удаляемой заметки:\t"))
                break
            except ValueError:
                print("не число")
        return del_id

    def read_dialog(self):
        while True:
            try:
                read_id = int(input("Введите индекс читаемой заметки:\t"))
                break
            except ValueError:
                print("не число")
        return read_id

    def correct_dialog(self):
        while True:
            try:
                read_id = int(input("Введите индекс читаемой заметки:\t"))
                break
            except ValueError:
                print("не число")
        text = input("Введите новый текст:\t")
        return tuple([read_id, text])

    def read_dialog_by_date(self):
        while True:
            try:
                user_date = (int(i) for i in input("Введите дату в формате ГГГГ-ММ-ЧЧ:\t").split("-"))
                user_date = list(user_date)
                d = date(user_date[0], user_date[1], user_date[2])
                break
            except ValueError:
                print("не число календаря")
        return d

    def console_printer(self, to_print_string: str):
        len_c = self.__console_length
        string_list = to_print_string.split('\n')
        for item in string_list:
            if len(item) <= len_c:
                print(item)
            else:
                temp = len(item) // len_c + 1
                string_gen = (item[len_c * i:len_c * i + len_c] for i in range(temp))
                for item2 in string_gen:
                    print(item2)

    def console_single_print(self, note: LibOfClass.SingleNote, id1=''):
        print(id1 + '\t', end='')
        text = note.get_date() + "\t" + note.get_title() + ':\t' + note.get_text()
        self.console_printer(text)

    def console_full_note(self, notepad: LibOfClass.NotePad):
        for k in notepad.get_keys():
            self.console_single_print(notepad.read_note(k), k.__str__())

    def console_test(self):
        string_to_print = '''В рамках теста выполнены следующие действия:
>>MyNotePad = NotePad()
>>MyNotePad.add_new_note("1_st_note", "some note about something")
>>MyNotePad.add_new_note("2_nd_note", "some note about something")
>>MyNotePad.add_new_note("3_d_note", "some note about something")
>>MyNotePad.add_new_note("4_th_note", "some note about absolutely nothing")
>>MyNotePad.delete_note(2)
>>MyNotePad.correct_note_text(3, "2-nd note was deleted")
>>MyNotePad.correct_note_title(3, "New 2-nd note with id=3")
>>MyNotePad.add_new_note("5_th_note", "some note about anything")
>>MyNotePad.print_all_notes()
>>MyNotePad.read_note(10)\n'''
        self.console_printer(string_to_print)
