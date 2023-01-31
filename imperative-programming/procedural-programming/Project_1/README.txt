README
    task1:
        1. Citim numarul de la tastatura
            inst = 1675493376;
            (inst =  01100011110111100000000000000000)
        2. Determinam numarul de instructiuni ce vor fi executate
            mask02 = 11100000000000000000000000000000;
            mask02 = 01100000000000000000000000000000;
            mask02 = 00000000000000000000000000000011;
            N = 4
        3. Determinam instructiunile ce vor fi executate
            mask03 = 00011111111000000000000000000000;
            mask03 = 00000011110000000000000000000000;
            00 = 0; => +
            01 = 1; => -
            10 = 2; => *
            11 = 3; => /
            + - / *
        4. Determinam dimensiunea unui operand
            mask04 = 00000000000111100000000000000000;
            mask04 = 00000000000000000000000000001111;
            Dim = 16

    task2: 
        3. Determinam instructiunile ce vor fi executate
            In loc sa afisam instructiunile le punem in vectorul Operatii[];   
        5. Calculam de cate nr avem nevoie si le citim
        6. Calculam si citim care sunt operanzii
            Parcurgem nr_de_introdus si ii divizam in 16/Dim segmente;
            Atribuim fiecare segment in parte vectorului operanzi[];
        7. Calculam rezultatul dintre operanzi si operatiile corespunzatoare
    task3:
        6. Calculam si citim care sunt operanzii
            Am atribuit lui biti[] fiecare bit din nr_de_introdus
            Conform Dim transormam valorile lui biti[] convertind in baza 10;
    task4:
        7. Calculam rezultatul dintre operanzi si operatiile corespunzatoare
            Evaluam in primul rand * si / transformandulie in ' '
            Trasformam ' ' in + sau -