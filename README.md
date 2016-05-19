#Generatore di modelli NetLogo

###Behaviors 
Evacuate e Visit sono trattate indistintamente per il momendo, per entrambi si usa solo la lista dei CoreInterestPoints

###Builder
Si usa la libreria JDOM inclusa nel folder *lib*
Aggiunte interfacce Builder e Parser 

###Graph
Rappresenta l'informazione letta dal documento XML

###Visitor
Due visitor separati che generano due file netlogo diversi, uno per la generazione della mappa (NetLogoGrahVisitor) e uno per il comportamento degli attori nell'ambiente(NetLogoBehaviorGraph).

-Da aggiustare l'INPUT-STREAM, in modo da avere un unico file.

##Data

Gli inputs e gli outputs sono distinti con A per il generatore della mappa e B per *grid-multi-destination*



