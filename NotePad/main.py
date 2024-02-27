import LibOfClass
import json
import Presenter
import sys
# Реализовать консольное приложение заметки, с сохранением, чтением,
# добавлением, редактированием и удалением заметок.
''' реализованы методы: 
void add_new_note(title: str, text: str), 
int count_notes(), 
void delete_note(id_note: int), 
SingleNote pop_note(id_note: int), SingleNote read_note(id_note: int),
void correct_note_title(id_note: int), 
void correct_note_text(id_note: int) 
'''

# Сохранение заметок необходимо сделать в
# формате json или csv формат (разделение полей рекомендуется делать через
# точку с запятой).
'''Реализован формат JSON. Осуществлен Encoding для двух классов SingleNote и NotePad'''
# Реализацию пользовательского интерфейса студент может
# делать как ему удобнее, можно делать как параметры запуска программы
# (команда, данные), можно делать как запрос команды с консоли и
# последующим вводом данных, как-то ещё, на усмотрение студента

try:
    with open("Notes.txt", "r") as f:
        temp = json.load(f)
        file_note_pad = LibOfClass.NotePad(LibOfClass.load_notepad_data(temp['_NotePad__data']),
                                           temp['_NotePad__last_note'])
except FileNotFoundError:
    print("Notes file not found, new NotePad created")
    with open("Notes.txt", 'w') as f:
        file_note_pad = LibOfClass.return_test_notePad()
        json.dump(file_note_pad, f, cls=LibOfClass.NotePadEncoder)
    f.close()

except RuntimeError:
    print("Something goes wrong, file is empty or wrong")

presenter = Presenter.Presenter(sys.argv, file_note_pad)
presenter.get_command()
with open("Notes.txt", "w") as f:
    json.dump(file_note_pad, f, cls=LibOfClass.NotePadEncoder)


