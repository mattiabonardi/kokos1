     V* ==============================================================
     V* MODIFICHE Ril.  T Au Descrizione
     V* gg/mm/aa  nn.mm i xx Breve descrizione
     V* ==============================================================
     V* 24/06/19  V5R1    AS Creazione
     V* 28/06/19  000942  AS Aggiunta funzione per matrice e clear
     V* 28/06/19  V5R1   BMA Check-out 000942 in SMEUP_SVI
     V* 28/06/19  000946  AS Trasformata ENTRY da Ds a campi singoli
     V* 28/06/19  V5R1   BMA Check-out 000946 in SMEUP_SVI
     V* 02/07/19  000954  AS Sostituita MOVEL con routine rudimentale per imitare Â£TNU
     V* 02/07/19  V5R1    CM Check-out 000954 in SMEUP_SVI
     V* 02/07/19  000957  AS Corretto XML, elimnato TAG (non supportato)
     V* 03/07/19  V5R1    CM Check-out 000957 in SMEUP_SVI
     V* 05/07/19  V5R1    BMA Renamed JD_010 in MUTE11_10
     V* 11/07/19  000983  AS Aggiunta Fu/Me con CALL a Fibonacci
     V* 11/07/19  V5R1   BMA Check-out 000983 in SMEUP_TST
     V* 11/07/19  000987  AS Eliminata Â£TNU
     V* 11/07/19  V5R1   BMA Check-out 000987 in SMEUP_TST
     V* 11/07/19  000988  AS Corretto errore
     V* 11/07/19  V5R1   BMA Check-out 000988 in SMEUP_TST
     V* ==============================================================
      * Nota sulle COPY
      * Avrei voluto lasciare l'inclusione di tutte le COPY, dato che vengono ignorate
      * dall'interprete. Non Ã¨ possibile farlo con tutte. Ad esempio le Â£JAX contengono la
      * ENTRY e questo andrebbe in conflitto (comilando in AS) con la ENTRY inserita appositamente
      * Commento l'EXSR delle COPY dato che genererebbe errore in interpretato
      * ==============================================================
     H/COPY QILEGEN,Â£INIZH
      *--------------------------------------------------------------------------------------------*
      * ENTRY RIDOTTA (Solo Â£UIBDS, a sua volta ridotta nel senso che molti campi non verranno
      *                valorizzati)
      *               Per ora l'interprete non gestisce le DS, quindi non posso usare la DS
      *--------------------------------------------------------------------------------------------*
     D** Â£UIBDS_MU       DS         31000
      *** Reserved:
     D**  $$R001                       10
      *** Reserved:
     D**  $$R002                       10
      ***
      *** Componente:
     D**  $UIBPG                       10
      *** Programma:
     D**  $UIBFU                       10
      *** Funzione/metodo:
     D**  $UIBME                       10
      ***
      *** T1:
     D**  $UIBT1                        2
      *** P1:
     D**  $UIBP1                       10
      *** K1:
     D**  $UIBK1                       15
      *** T2:
     D**  $UIBT2                        2
      *** P2:
     D**  $UIBP2                       10
      *** K2:
     D**  $UIBK2                       15
      *** T3:
     D**  $UIBT3                        2
      *** P3:
     D**  $UIBP3                       10
      *** K3:
     D**  $UIBK3                       15
      ***
      *** P:
     D**  $UIBPA                      256
      ***
      *** Reserved:
     D**  $$R003                        1
      *** Reserved:
     D**  $$R004                        1
      *** Reserved:
     D**  $$R005                        7
      *** Reserved:
     D**  $$R006                       10
      *** Reserved:
     D**  $$R007                       10
      ***
      *** T4:
     D**  $UIBT4                        2
      *** P4:
     D**  $UIBP4                       10
      *** K4:
     D**  $UIBK4                       15
      *** T5:
     D**  $UIBT5                        2
      *** P5:
     D**  $UIBP5                       10
      *** K5:
     D**  $UIBK5                       15
      *** T6:
     D**  $UIBT6                        2
      *** P6:
     D**  $UIBP6                       10
      *** K6:
     D**  $UIBK6                       15
      ***
      *** Reserved:
     D**  $$R008                       15
      *** Reserved:
     D**  $$R009                       15
      *** Reserved:
     D**  $$R010                       58
      *** Reserved:
     D**  $$R011                        4
      *** Reserved:
     D**  $$R012                      256
      *** Reserved:
     D**  $$R013                        3
      ***
      *** INPUT:
     D**  $UIBD1              1001  31000
      *--------------------------------------------------------------------------------------------*
      * ENTRY ESPLOSA Dato che non posso mettere una DS in ENTRY, metto solo i campi che mi
      *                interessano.
      *--------------------------------------------------------------------------------------------*
      * Componente:
     D  $UIBPG         S             10
      * Programma:
     D  $UIBFU         S             10
      * Funzione/metodo:
     D  $UIBME         S             10
      *
      * T1:
     D  $UIBT1         S              2
      * P1:
     D  $UIBP1         S             10
      * K1:
     D  $UIBK1         S             15
      * T2:
     D  $UIBT2         S              2
      * P2:
     D  $UIBP2         S             10
      * K2:
     D  $UIBK2         S             15
      * T3:
     D  $UIBT3         S              2
      * P3:
     D  $UIBP3         S             10
      * K3:
     D  $UIBK3         S             15
      *
      * P:
     D  $UIBPA         S            256
      *
      * T4:
     D  $UIBT4         S              2
      * P4:
     D  $UIBP4         S             10
      * K4:
     D  $UIBK4         S             15
      * T5:
     D  $UIBT5         S              2
      * P5:
     D  $UIBP5         S             10
      * K5:
     D  $UIBK5         S             15
      * T6:
     D  $UIBT6         S              2
      * P6:
     D  $UIBP6         S             10
      * K6:
     D  $UIBK6         S             15
      *
      * INPUT:
     D  $UIBD1         S          30000
      *--------------------------------------------------------------------------------------------*
      * Contatore temporaneo
     D  $X             S              5  0
      * Contatore temporaneo
     D  $Y             S              5  0
      * Contatore di chiamate
     D  $COUNT         S              5  0
      * Collaboratore ricevuto in input
     D  $$COL          S             15
      * Numero ricevuto in input
     D  $$NUM          S             21
      * Parte di XML con il "payload" (senza header, ecc.)
     D  $$XMLPAYLOAD   S          30000
      * XML da spedire sulla coda
     D  $$XML          S          30000
      * Chiudere in LR?
     D  CLOLR          S              1N
      *--------------------------------------------------------------------------------------------*
      * ENTRY MUTE11_11A
     D  U$INNR         S             21  0
     D  U$FIBO         S             21  0
      *--------------------------------------------------------------------------------------------*
      ***/COPY QILEGEN,Â£JAX_D
      /COPY QILEGEN,Â£TABJATDS
      /COPY QILEGEN,Â£TABBÂ£1DS
      /COPY QILEGEN,Â£PDS
      *--------------------------------------------------------------------------------------------*
      * Impostazioni iniziali
     C                   EXSR      IMP0
      *
1    C                   SELECT
      * Funzione esempio
1x   C                   WHEN      %SUBST($UIBME:1:3)='ESE'
1    C                   SELECT
      * Metodo albero
1x   C                   WHEN      %SUBST($UIBME:5:3)='TRE'
     C                   EXSR      FESETRE
      * Metodo matrice
1x   C                   WHEN      %SUBST($UIBME:5:3)='MAT'
     C                   EXSR      FESEMAT
      * Metodo CLR (chiudi in LR)
1x   C                   WHEN      %SUBST($UIBME:5:3)='CLR'
     C                   EXSR      FESECLR
1e   C                   ENDSL
      * Richiamo di Fibonacci
1x   C                   WHEN      %SUBST($UIBME:1:3)='FIB'
     C                   EXSR      FFIB
1e   C                   ENDSL
      *
     C                   EXSR      FIN0
      *
     C                   IF        CLOLR=*ON
     C                   SETON                                        LR
     C                   ELSE
     C                   SETON                                        RT
     C                   ENDIF
     C/COPY QILEGEN,Â£INZSR
      /COPY QILEGEN,Â£RITES
      /COPY QILEGEN,Â£DEC
      *--------------------------------------------------------------*
    RD*  Impostazioni iniziali
      *--------------------------------------------------------------*
     C     IMP0          BEGSR
      *
     C***                EXSR      Â£JAX_IMP0
      * Di default devo chiudermi in RT
     C                   EVAL      CLOLR=*OFF
     C                   ENDSR
      *--------------------------------------------------------------*
    RD*  Albero con collaboratore ricevuto in input e contatore incrementato
      *--------------------------------------------------------------*
     C     FESETRE       BEGSR
      * Estraggo collaboratore da INPUT
     C                   EXSR      DETCOL
      * Incremento contatore
     C                   EVAL      $COUNT=$COUNT+1
      * Emetto albero con collaboratore e contatore
     C***                EVAL      Â£JAXT1='CN'
     C***                EVAL      Â£JAXP1='COL'
     C***                EVAL      Â£JAXK1='ARRSTE'
     C***                EVAL      Â£JAXD1='Contatore: '
     C***                          +%TRIM(%CHAR($COUNT))
     C***                EXSR      Â£JAX_ADDO
     C                   EVAL      $$XMLPAYLOAD='<Oggetto Tipo="CN"'
     C                             +' Parametro="COL" Codice="'
     C                             +%TRIM($$COL)+'"'
     C                             +' Testo="Contatore: '
     C                             +%TRIM(%CHAR($COUNT))+'"'
     C                             +' />'
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD*  Matrice con collaboratore ricevuto in input e contatore incrementato
      *--------------------------------------------------------------*
     C     FESEMAT       BEGSR
      * Estraggo collaboratore da INPUT
     C                   EXSR      DETCOL
      * Incremento contatore
     C                   EVAL      $COUNT=$COUNT+1
      * Emetto matrice con collaboratore e contatore
     C***                 EVAL      Â£JAXSWK=SWK001
     C***                 EXSR      Â£JAX_AGRI
     C                   EVAL      $$XMLPAYLOAD='<Griglia><Colonna Cod="XXCONT"'
     C                              +' Txt="Contatore" Lun="5" IO="O"'
     C                              +' Ogg="NR"/>'
     C                              +' <Colonna Cod="XXCOLL" Txt="Collaboratore"'
     C                              +' Lun="15" IO="O" Ogg="CNCOL"/>'
     C                              +' </Griglia>'
     C***                 EXSR      Â£JAX_ARIG_I
     C                   EVAL      $$XMLPAYLOAD=%TRIM($$XMLPAYLOAD)
     C                              +'<Righe>'
     C***                 EVAL      Â£JaxCP=%TRIM(%CHAR($COUNT))
     C***                                 +'|'+%TRIM($$COL)
     C***                 EXSR      Â£JAX_ARIG
     C                   EVAL      $$XMLPAYLOAD=%TRIM($$XMLPAYLOAD)
     C                              +'<Riga Fld="'
     C                              +%TRIM(%CHAR($COUNT))
     C                              +'|'+%TRIM($$COL)+'"'
     C***                 EXSR      Â£JAX_ARIG_F
     C                   EVAL      $$XMLPAYLOAD=%TRIM($$XMLPAYLOAD)
     C                              +'/></Righe>'
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD*  Reset (chiudo in LR)
      *--------------------------------------------------------------*
     C     FESECLR       BEGSR
      *
      * Imposto chiusura in LR per resettare contatore
     C                   EVAL      CLOLR=*ON
      * Emetto albero con reset contatore
     C***                 EVAL      Â£JAXT1=*BLANKS
     C***                 EVAL      Â£JAXP1=*BLANKS
     C***                 EVAL      Â£JAXK1=*BLANKS
     C***                 EXSR      Â£JAX_ADDO
     C                   EVAL      $$XMLPAYLOAD='<Oggetto Tipo=""'
     C                             +' Parametro="" Codice=""'
     C                             +' Testo="SETON LR"'
     C                             +' />'
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD*  Estraggo collaboratore da INPUT
      *--------------------------------------------------------------*
     C     DETCOL        BEGSR
      *
     C***                EVAL      $$COL=P_RXATT(Â£UIBD1:'COL:'')
     C                   CLEAR                   $$COL
     C                   EVAL      $X=%SCAN('COL(':$UIBD1)
     C                   IF        $X>0
     C                   EVAL      $Y=%SCAN(')':$UIBD1:$X+4)
     C                   IF        $Y>0
     C                   EVAL      $$COL=%SUBST($UIBD1:$X+4:$Y-$X-4)
     C                   ENDIF
     C                   ENDIF
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD*  Estraggo Numero da INPUT
      *--------------------------------------------------------------*
     C     DETNR         BEGSR
      *
     C***                EVAL      $$NUM=P_RXATT(Â£UIBD1:'NUM:'')
     C                   CLEAR                   $$NUM
     C                   EVAL      $X=%SCAN('NUM(':$UIBD1)
     C                   IF        $X>0
     C                   EVAL      $Y=%SCAN(')':$UIBD1:$X+4)
     C                   IF        $Y>0
     C                   EVAL      $$NUM=%SUBST($UIBD1:$X+4:$Y-$X-4)
     C                   ENDIF
     C                   ENDIF
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD*  Esecuzione di Fibonacci
      *--------------------------------------------------------------*
     C     FFIB          BEGSR
      * Estraggo numero di cui calcolare il numero di Fibonacci
     C                   EXSR      DETNR
      * Trasformo in numerico
     C                   EVAL      U$INNR = %DEC($$NUM : 21 : 0)
      * Chiamo il programma che calcola il numero di fibonacci
     C                   CALL      'MUTE11_11A'
     C                   PARM                    U$INNR
     C                   PARM                    U$FIBO
      * Emetto albero con numero iniziale e suo fibonacci
     C***                EVAL      Â£JAXT1='**'
     C***                EVAL      Â£JAXP1=*BLANKS
     C***                EVAL      Â£JAXK1=*BLANKS
     C***                EVAL      Â£JAXD1=
     C***                          'FIBONACCI OF: '
     C***                          +%TRIM($$NUM)
     C***                          +' IS '
     C***                          +%TRIM(%CHAR(U$FIBO))
     C***                EXSR      Â£JAX_ADDO
     C                   EVAL      $$XMLPAYLOAD='<Oggetto Tipo="**"'
     C                             +' Parametro="" Codice="A"'
     C                             +' Testo="'
     C                             +'Fibonacci of: '                            COSTANTE
     C                             +%TRIM($$NUM)
     C                             +' is '
     C                             +%TRIM(%CHAR(U$FIBO))
     C                             +'"/>'
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD*  Chiusura e invio su coda del buffer residuo
      *--------------------------------------------------------------*
     C     FIN0          BEGSR
      *
     C***                EXSR      Â£JAX_FIN0
      * Aggiungo header e footer a payload
     C                   EVAL      $$XML='<?xml version="1.0"'
     C                             +' encoding="ISO-8859-15"?>'
     C                             +' <Base Testo="- ">'
     C                             +' <Service Titolo1="" Titolo2=""'
     C                             +' Funzione="F('
     C                             +%TRIM($UIBPG)+';'
     C                             +%TRIM($UIBFU)+';'
     C                             +%TRIM($UIBME)+') 1(;;)'
     C                             +' INPUT('
     C                             +%TRIM($UIBD1)+')'+'"'
     C                             +' Servizio="'
     C                             +%TRIM($UIBFU)
     C                             +'" TSep="." DSep=","'
     C                             +' IdFun="0000000000000" NumSes="000000" />'
     C                             +%TRIM($$XMLPAYLOAD)
     C                             +'</Base>'
      * Scrittura XML su coda
     C                   CALL      'JAX_SND'
     C                   PARM                    $$XML
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD* Routine impostazioni di avvio programma
      *--------------------------------------------------------------*
     C     Â£INIZI        BEGSR
      *
     C***                EXSR      Â£JAX_INZP
     C***                EXSR      Â£JAX_INZ
      * ENTRY
     C     *ENTRY        PLIST
     C***                PARM                    Â£UIBDS_MU
     C                   PARM                    $UIBPG
     C                   PARM                    $UIBFU
     C                   PARM                    $UIBME
     C                   PARM                    $UIBT1
     C                   PARM                    $UIBP1
     C                   PARM                    $UIBK1
     C                   PARM                    $UIBT2
     C                   PARM                    $UIBP2
     C                   PARM                    $UIBK2
     C                   PARM                    $UIBT3
     C                   PARM                    $UIBP3
     C                   PARM                    $UIBK3
     C                   PARM                    $UIBPA
     C                   PARM                    $UIBT4
     C                   PARM                    $UIBP4
     C                   PARM                    $UIBK4
     C                   PARM                    $UIBT5
     C                   PARM                    $UIBP5
     C                   PARM                    $UIBK5
     C                   PARM                    $UIBT6
     C                   PARM                    $UIBP6
     C                   PARM                    $UIBK6
     C                   PARM                    $UIBD1
      *
     C                   CLEAR                   $COUNT
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD* Log Specifico applicazione
      *--------------------------------------------------------------*
     C     Â£JAX_LOG      BEGSR
     C                   ENDSR
      *
      ***/COPY QILEGEN,Â£JAX_C
      ***/COPY QILEGEN,Â£JAX_O