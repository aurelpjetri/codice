Analisi del cocumento XML attraverso la libreria JDOM2 e costruzione del grafo attraverso ConcreteBuilder

package behaviors da sistemare

Nel package grafo è presente anche un esempio di documento XML su cui mi sono basato per la scrittura del parser e un documento xml sbagliato usato per testare le eccezioni lanciate.

{Visitor parzialmente funzionante. Insicuro sulla correttezza, nei visit nei vari nodi non chiudo la connessione, poiché questo fa fallire lo stream sui nodi successivi al primo. 
La clausola Try/Finally è usata solo nel visit del Grafo, in cui si chiamano gli accept (e quindi i visit) sui vari nodi. Quindi la connessione è chiusa ( 'outputStream.close()' ) solo alla fine del visit del grafo.}

Update: Il Visitor dovrebbe essere corretto.

Behaviors: 
- Aggiunti metodi in builder per costruizione behaviors. 

- Aggiunta lista di behaviors in Graph e metodi utili.

- Modificato XMLParser per la lettura dei behaviors



