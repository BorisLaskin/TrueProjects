from dataclasses import dataclass
from datetime import datetime
import json


# Заметка должна содержать идентификатор, заголовок, тело заметки и дату/время создания или
# последнего изменения заметки.
@dataclass
# Структура SingleNote является базовой единицей записи имеет всего два метода: конструктор и Преобразование к строке
class SingleNote:
    __id_note: int
    __title: str
    __text: str
    __date_note: str

    def __init__(self, id_note: int, title: str, text: str, date_note=datetime.now()):
        self.__id_note = id_note
        self.__title = title
        self.__text = text
        self.__date_note = self.date_to_str(date_note)
        # тип данный datetime not encode to JSON. Принято решение гнать все в строку

    def date_to_str(self, date: datetime):
        return date.__str__().split(' ')[0]

    def __str__(self):
        return str(self.__id_note) + ":\t" + self.__title + ":\t" + self.__text + "/\t" + self.__date_note

    def get_title(self):
        return self.__title

    def get_text(self):
        return self.__text

    def get_date(self):
        return self.__date_note

    def set_text(self, text=''):
        self.__text = text
        self.__date_note = self.date_to_str(datetime.now())

    def set_title(self, title=''):
        self.__title = title
        self.__date_note = self.date_to_str(datetime.now())



# Статический метод преобразования данных из JSON в _NotePad__data
def load_notepad_data(data_dict):
    result_data = dict()
    for (k, v) in data_dict.items():
        result_data[int(k)] = SingleNote(int(k), v['_SingleNote__title'], v['_SingleNote__text'], v['_SingleNote__date_note'])
    return result_data


@dataclass
class NotePad:
    __data: dict[int, SingleNote]
    __last_note: int  # Переменная исключает факт создания SingleNote с одинаковым id

    # в тоже время среди индексов допустимы пропуски

    def __init__(self, data=None, last_note=0):
        if data is None:
            data = dict()
        self.__data = data
        self.__last_note = last_note

    def add_new_note(self, title: str, text: str):
        self.__last_note += 1
        temp_node = SingleNote(id_note=self.__last_note, title=title, text=text)
        self.__data[self.__last_note] = temp_node

    def count_notes(self):
        return len(self.__data.keys())

    def delete_note(self, id_note):
        try:
            del self.__data[id_note]
        except KeyError:
            print(f"ERROR:Нет записки с ID={id_note}")

    def pop_note(self, id_note):
        try:
            temp = self.__data[id_note]
            self.delete_note(id_note)
            return temp
        except KeyError:
            print(f"ERROR:Нет записки с ID={id_note}")

    def read_note(self, id_note):
        try:
            return self.__data[id_note]
        except KeyError:
            print(f"ERROR:Нет записки с ID={id_note}")

    def correct_note_text(self, id_note, text):
        try:
            self.__data[id_note].set_text(text)
        except KeyError:
            print(f"ERROR:Нет записки с ID={id_note}")

    def correct_note_title(self, id_note, title):
        try:
            self.__data[id_note].set_title(title)
        except KeyError:
            print(f"ERROR:Нет записки с ID={id_note}")

    def print_all_notes(self):
        for id_note in self.__data.keys():
            print(self.__data[id_note])

    def get_keys(self):
        return self.__data.keys()

    def read_id_by_date(self, datetime_text):
        list_id=list()
        for id_note in self.__data.keys():
            if self.__data[id_note].get_date() == datetime_text:
                list_id.append(id_note)
        return list_id


class NotePadEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, NotePad):
            return obj.__dict__
        elif isinstance(obj, SingleNote):
            return SingeNoteEncoder().default(obj)
        return json.JSONEncoder.default(self, obj)


class SingeNoteEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, SingleNote):
            return obj.__dict__
        return json.JSONEncoder.default(self, obj)


def return_test_notePad():
    MyNotePad = NotePad()
    MyNotePad.add_new_note("1_st_note", "some note about something")
    MyNotePad.add_new_note("2_nd_note", "some note about something")
    MyNotePad.add_new_note("3_d_note", "some note about something")
    MyNotePad.add_new_note("4_th_note", "some note about absolutely nothing///some note about absolutely nothing///some note about absolutely nothing///some note about absolutely nothing///some note about absolutely nothing///some note about absolutely nothing///some note about absolutely nothing")
    MyNotePad.delete_note(2)
    MyNotePad.correct_note_text(3, "2-nd note was deleted")
    MyNotePad.correct_note_title(3, "New 2-nd note with id=3")
    MyNotePad.add_new_note("5_th_note", "some note about anything")
    return MyNotePad
