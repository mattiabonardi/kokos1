::G.SEZ Pos(1)
::G.SUB.LAB
::D.OGG D(CALCOLO NUMERO DI FIBONACCI)

::G.SEZ Pos(2)
::G.SEZ Pos(2A)
::G.SUB.FLD Tit="Calcolo : "
::G.SET.FLD Type="Itx" ShowSubmit="Yes" SubmitPosition="Left"
::G.DIN When="Click" Where="RES"
::D.OGG D()

::G.SEZ Pos(2B) 
::G.SUB.LAB Tit="Risultato : " Id="RES" Load="D"
::D.FUN.STD F(TRE;MUTE11_10;FIB) INPUT(NUM([K1]))