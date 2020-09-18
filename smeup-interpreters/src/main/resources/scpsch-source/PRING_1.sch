::G.SEZ Pos(A)

::G.SEZ Pos(A1)
 ::G.SEZ Pos(A11)
  ::G.SUB.LAB Tit="*NONE" Id="LBL01"
  ::G.SET.LAB FontColor="R255G051B051" FontSize="14" FontBold="Yes" FontULine="No" FontItalic="Yes" Align="CENTER" Border="No"
  ::D.OGG D(Prenota qui la tua presenza in Sme.UP)

::G.SEZ Pos(A2)

 ::G.SEZ Pos(A21)
  ::G.SUB.BTN Tit="*NONE" Id="BTN1"
  ::G.SET.BTN ShowText="Yes" ShowIcon="Yes" FillSpace="Yes" BackColor="R000G204B000" AutoSize="Yes"
  ::G.DIN Where="SCH1" When="Click" TitleLock="Yes" Ssc.Var="VAL1([Tx]) VAL2(BRE) VAL3(GEN) "
 ::D.OGG D(Brescia)

 ::G.SEZ Pos(A22)
  ::G.SUB.BTN Tit="*NONE" Id="BTN2"
  ::G.SET.BTN ShowText="Yes" ShowIcon="Yes" FillSpace="Yes" BackColor="R000G204B000" AutoSize="Yes"
  ::G.DIN Where="SCH1" When="Click" TitleLock="Yes" Ssc.Var="VAL1([Tx]) VAL2([K1]) VAL3(AGO) "
  ::D.OGG D(Erbusco) K(ERB)
  ::D.OGG K(LEC) D(Lecco)
  ::D.OGG K(MOD) D(Modena)
  ::D.OGG K(NOV) D(Nova Milanese)
  ::D.OGG K(PAR) D(Parma)
  ::D.OGG K(REG) D(Reggio Emilia)
  ::D.OGG K(TOR) D(Rivoli - Torino)
  ::D.OGG K(ROM) D(Roma)
  ::D.OGG K(SAV) D(Savigliano)
  ::D.OGG K(UDI) D(Udine - Sinte.sys)
  ::D.OGG K(VER) D(Vercelli)
  ::D.OGG K(VIG) D(Vigonza)
  ::D.OGG K(VIL) D(Villaverla Vicenza)

::G.SEZ Pos(A23)
 ::G.SUB.BTN Tit="*NONE" Id="BTN3"
  ::G.SET.BTN ShowText="Yes" ShowIcon="Yes" FillSpace="Yes" BackColor="R000G204B000" AutoSize="Yes"
  ::D.OGG D(Fibonacci) E(F(EXD;*SCO;) 2(MB;SCP_SCH;TESTFIBO))

::G.SEZ Pos(B)
::G.SEZ Pos(B1) Dim(60%)
::G.SUB.SCH Tit="*NONE" Id="SCH1" Load="D"
::D.FUN.STD F(EXD;*SCO;) 2(MB;SCP_SCH;PRING_1) 4(;;CAL)
 ::G.SEZ Pos(B2)
  ::G.SUB.SCH Tit="*NONE" Id="SCH2" Load="D"
  ::D.FUN.STD F(EXD;*SCO;) 2(MB;SCP_SCH;PRING_1) 4(;;DET)
 ::G.SEZ Pos(B3)
  ::G.SUB.SCH Tit="*NONE" Id="SCH3" Load="D"
  ::D.FUN.STD F(EXD;*SCO;) 2(MB;SCP_SCH;PRING_1) 4(;;COL)

::I.SCH Nam(CAL)

 ::G.SEZ Pos(1) Dim(20%)
  ::G.SUB.LAB Tit="*NONE" Id="LBL02"
   ::D.OGG D(Hai selezionato la sede di [VAL1])

 ::G.SEZ Pos(2)
  ::G.SUB.CAL Tit="*NONE" Id="CAL1"
  ::G.DIN When="Change"
  ::G.DIN When="Click" Exec="F(FBK;LOSER_FL;WRIADD) 2(;;[*USER]) 3(;;[XXDAT1])  4(;;[XXCOD1]) NOTIFY(*SUB)"
  ::G.DIN Where="SCH2,SCH3" When="Click" TitleLock="Yes" Sch.Var="Sede([XXSEDE]) Data([XXDAT1]) Attuale([XXPATT]) Attesa([XXPATE]) Prenotato([XXPPRE]) CodSede([XXCOD1])"
  ::D.FUN.STD F(EXB;LOSER_FL;MATPRV) 2(;;[VAL2]) 3(;;[VAL1]) 4(;;[VAL3])

::I.SCH.END

::I.SCH Nam(DET)

 ::G.SEZ Pos(1) Dim(10%)
  ::G.SUB.LAB Tit="*NONE" Id="LBL03"
   ::D.OGG D(Hai selezionato la sede di [Sede])

 ::G.SEZ Pos(2)
   ::G.SUB.BOX Tit="*NONE" Id="BOX01"
   ::G.SET.BOX Layout="PRING_1"
  ::D.FUN.STD F(EXB;LOSER_FL;READRW) 2(;;[XXDAT1]) 3(;;[XXCOD1]) 4(;;[XXSEDE])

 ::G.SEZ Pos(3)
  ::G.SUB.BTN Tit="*NONE" Id="BTN15"
   ::G.DIN When="Click" Exec="F(FBK;LOSER_FL;WRIUPD) 2(;;[*USER]) 3(;;[Data]) 4(;;[CodSede]) NOTIFY(BOX01\SCH1\SCH3)"
   ::D.OGG D(Conferma)

 ::G.SEZ Pos(4)
  ::G.SUB.BTN Tit="*NONE" Id="BTN16"
   G.DIN When="Click" Exec="F(FBK;LOSER_FL;DELREC) 2(;;[*USER]) 3(;;[Data]) 4(;;[CodSede]) NOTIFY(BOX01\SCH1\SCH3)"
   ::D.OGG D(Cancella Prenotazione)
 ::I.SCH.END

::I.SCH Nam(COL)
::G.SEZ Pos(1) Dim(10%)
   ::G.SUB.LAB Tit="*NONE" Id="LBL04"
   ::G.SET.LAB FontName="Bodoni MT" FontColor="R051G000B204" FontSize="25" FontBold="Yes" FontItalic="Yes"
   ::D.OGG D(Elenco dei Collaboratori)
   ::D.OGG D(Data:[Data])

::G.SEZ Pos(2)
   ::G.SUB.BOX Tit="*NONE" Id="BOX02"
   ::G.SET.BOX Layout="PRING_2"
   D.FUN.STD F(EXB;LOSER_FL;MATCOL) 2(;;[XXDAT1]) 3(;;[XXCOD1])

::I.SCH.END