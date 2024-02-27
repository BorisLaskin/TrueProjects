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
void correct_note_text(id_note: int) и др.
'''

# Сохранение заметок необходимо сделать в
# формате json или csv формат (разделение полей рекомендуется делать через
# точку с запятой).
'''Реализован формат JSON. Осуществлен Encoding для двух классов SingleNote и NotePad
Интерфейс реализован в форме python main.py --key, где key - код аперрации, вызывающий диалог общения

Приложение должно запускаться без ошибок(готово), должно уметь сохранять данные
в файл (готово), уметь читать данные из файла(готово), делать выборку по дате(релизовано), 
выводить на экран выбранную запись(есть), выводить на экран весь список записок(есть), добавлять
записку(есть), редактировать ее и удалять(готово)'''

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


