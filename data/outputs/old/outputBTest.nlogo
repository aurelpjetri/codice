
;; the shortest path to the movers that need to
breed [beacons beacon]
breed [street-drawers street-drawer]
;; Movers are turtles that need to reach certain destinations

undirected-link-breed [streets street]

  destination-beacon ;; current destination moving towards
  destination-order ;; Table with strategies to calculate my next destination
  current-beacon ;; current beacon on my path
  undesired-street ;; street that is not included in my path, since i got stuck there
  mover-behavior
  patience ;; for how long can I be stuck before changing my direction
  movers-data ;; used for outputing some results

  weight
]
directed-streets-own [
  street-width

  drawer-destination
]
beacons-own [
  intersection-height
  interest-point?
  entry-ratios
]
;; Patches can be wall so they are now walkable
  wall

  ;; these need to be redefined

  destination-ordering

  world-offset
  global-street-width
  grid-size
  export-filename
  ;; Needed for results collection
  global-list-interest-points
]
;; IMPORT AND DEFAULTS

  import-world import-filename
end

;; ============
to go
  if use-entries = true [ new-movers-enter ]
  tick

  ask beacons with [exit-point? = true] [
    ask patches in-radius intersection-radius [
        if random-float 1 < exit-ratio and empty? destination-list [
          movers-data-collect exit-beacon
          die ] ]
  ]

  ask beacons with [entry-point? = true] [
      if random-float 1 < entry-ratio [
      ]
  ]

;; Populate the table movers-data with some information needed for results
  set movers-data table:make
  table:put movers-data "total_agents" count movers
    table:put movers-data (word "agents_" ?) count (movers with [destination-beacon = ?])
  ]

to movers-data-collect [passing-beacon]
  if table:get movers-data (word "exit_" passing-beacon) = 0
end
to movers-data-move-to-global
end
;; Given a list of behaviours and their probabilities
to-report get-random-mover-behaviour [iter-list]
  let acc 0
    set acc acc + (item 1 ?)
      [ report item 0 ? ]
end
;; MOVE PROCEDURE

to move
  update-next-patch
  ask movers with [should-move? = false] [ set patience patience - 1]
    orient-random-mover self
  ]

to check-if-involuntary-destination [agent tmp-beacon]
    let new-destination-list filter [? != tmp-beacon] [destination-list] of agent
               movers-data-collect tmp-beacon]
end
to update-next-patch
    set should-move? false
    ;; get an ordered list of patches where i could move
    foreach reverse oriented-list [
        face ?
      ]
  ]

;; wrt the difference between movers heading and the position of
;; visit
  report sort-on [abs subtract-headings [heading] of myself (towards myself + 180) mod 360] reachable-patches

to-report free-mover-patch [mover-patch]
    [report true]
end
;; ==================================================================
;; backtrack to the previous beacon and try to find a path that
;; ==================================================================
  ask previous-beacon [
    nw:set-context (beacons) ((link-set streets directed-streets) with [self != possible-undesired-street])

      ask myself [
        ;; then put the current street as undesirable
        ;; if my previous beacon is different from the current and destination I should move
        ;; in the full-path and it is more convenient to move towards the (item 1 full-path)
          [set current-beacon item 0 full-path]
            [set current-beacon item 1 full-path]
          ]
    ]
end
;; ==================================================================
;; ==================================================================

  let origin-beacon current-beacon
    [nw:weighted-distance-to origin-beacon "weight"] of ?1 <
  set destination-beacon item 0 destination-list

  set destination-beacon item 0 destination-list

;; SOME CONTROL PROCEDURES
to toggle-graph-view
  ask beacons with [interest-point? = false] [set hidden? not hidden?]

  toggle-graph-view
  ask one-of beacons with [interest-point? = false and entry-point? = false and exit-point? = false] [
  ]
  toggle-graph-view

;; RESULTS OUTPUT
;; Called at the end by the BehaviourSpace
to register-results
  file-open (word "experiment_" behaviorspace-experiment-name "_run_" behaviorspace-run-number ".csv")
  let onei first table:keys global-movers-results
  foreach table:keys global-movers-results [
  ]
  ;; close it
end
to-report to-csv [l]
end
;;=================== modificate
;; Check if I've reached the beacons and update my path,
to update-path
    let next-beacon current-beacon

    ask current-beacon [
        ;; set the previous beacon to the current-one, since we have reached it

        check-if-involuntary-destination current-mover self
        ;; calculate the full path to my destination
        let full-path nw:turtles-on-weighted-path-to [destination-beacon] of myself "weight"
        ;; If there are more than one beacon in the path I haven't reached my destination
        if not empty? but-first full-path

        ;; a destination-beacon has been reached, here we check with if for
        if self = [destination-beacon] of myself
            ;; remove the current destination-beacon from this list
              ifelse not empty? destination-list
			    [set-destination-min-distance]
                ;[ set destination-beacon one-of beacons with [exit-point? = true]]
          ] ]
    ]
  ]

  sprout-movers 1 [
    set previous-beacon current-beacon
    set speed 0.1

    ;; destination list is saved as a list so we can do more complex
    ;; By simply calling sort on an agentset, we get an ordered list
	;;------------------------------------------------------------------------------
    set mover-behavior get-random-mover-behaviour [entry-ratios] of current-beacon     
	set destination-list table:get behaviors-map mover-behavior
	

	

    set color [color] of destination-beacon
      [ set color lput 100 sublist color 0 3 ]

end

  let list-of-beacons []
    set list-of-beacons lput (item 0 sort (beacons-on patch (item 0 ?) (item 1 ?))) list-of-beacons
  report list-of-beacons

to default-configuration
  set-default-shape beacons "box"
  set-default-shape movers "circle"
  set global-crowd-max-at-patch 5
 
  set behaviors-map table:make
  table:put behaviors-map 0 get-interest-beacons map [ list (world-offset + item 0 ?) (world-offset + item 1 ?) ] [ [(10) (20)]  [(20) (20)] ]
  table:put behaviors-map 1 get-interest-beacons map [ list (world-offset + item 0 ?) (world-offset + item 1 ?) ] [ [(10) (10)]  [(20) (10)] ]
end
@#$#@#$#@
305
1173
-1
13.0
10
1
0
1
0
0
0
1
30.0
BUTTON
136
169
setup
1
OBSERVER
NIL
NIL

36
281
import-filename
1
String
SWITCH
223
256
use-exits
1

166
288
use-entries
0
-1000
TEXTBOX
193
211
12
1
TEXTBOX
33
65
12
1
BUTTON
274
307
go
1
OBSERVER
NIL
NIL

123
288
NIL
NIL
T
NIL
NIL
1
BUTTON
321
354
change-poi
1
OBSERVER
NIL
NIL

52
224
entry-ratio
0
0.061
1
HORIZONTAL
SLIDER
462
495
exit-ratio
0.1
0.001
NIL

65
237
global-patience
1
10
1
HORIZONTAL
@#$#@#$#@

















@#$#@#$#@
true
Polygon -7500403 true true 150 5 40 250 150 205 260 250
airplane
0

true
Polygon -7500403 true true 150 0 0 150 105 150 105 293 195 293 195 150 300 150
box
0
Polygon -7500403 true true 150 135 15 75 150 15 285 75
Line -16777216 false 150 285 150 135
Line -16777216 false 150 135 285 75
bug
0
Circle -7500403 true true 110 127 80
Line -7500403 true 150 100 80 30

true
Polygon -7500403 true true 150 165 209 199 225 225 225 255 195 270 165 255 150 240
Polygon -7500403 true true 139 148 100 105 55 90 25 90 10 105 10 135 25 180 40 195 85 194 139 163
Polygon -16777216 true false 150 255 135 225 120 150 135 120 150 105 165 120 180 150 165 225
Line -16777216 false 150 105 195 60

false
Polygon -7500403 true true 300 180 279 164 261 144 240 135 226 132 213 106 203 84 185 63 159 50 135 50 75 60 0 150 0 165 0 225 300 225 300 180
Circle -16777216 true false 30 180 90
Circle -7500403 true true 47 195 58

false
Circle -7500403 true true 0 0 300
circle 2
0
Circle -16777216 true false 30 30 240
cow
0
Polygon -7500403 true true 73 210 86 251 62 249 48 208

false
Circle -7500403 true true 0 0 300
dot
0

false
Circle -7500403 true true 8 8 285
Circle -16777216 true false 180 75 60

false
Circle -7500403 true true 8 7 285
Circle -16777216 true false 180 75 60

false
Circle -7500403 true true 8 8 285
Circle -16777216 true false 180 75 60

false
Polygon -1 true false 44 131 21 87 15 86 0 120 15 150 0 180 13 214 20 212 45 166
Polygon -1 true false 75 45 83 77 71 103 86 114 166 78 135 60
Circle -16777216 true false 215 106 30
flag
0
Polygon -7500403 true true 90 150 270 90 90 30
Line -7500403 true 75 45 90 45
flower
0
Circle -7500403 true true 85 132 38
Circle -7500403 true true 192 85 38
Circle -7500403 true true 177 40 38
Circle -7500403 true true 70 85 38
Circle -7500403 true true 96 51 108
Polygon -10899396 true false 189 233 219 188 249 173 279 188 234 218

false
Rectangle -7500403 true true 45 120 255 285
Polygon -7500403 true true 15 120 150 15 285 120

false
Polygon -7500403 true true 150 210 135 195 120 210 60 210 30 195 60 180 60 165 15 135 30 120 15 105 40 104 45 90 60 90 90 105 105 120 120 120 105 60 120 60 135 30 150 15 165 30 180 60 195 60 180 120 195 120 210 105 240 90 255 90 263 104 285 105 270 120 285 135 240 165 240 180 270 195 240 210 180 210 165 195

true
Line -7500403 true 150 0 150 300
line half
0

false
Polygon -7500403 true true 150 15 15 120 60 285 240 285 285 120
person
0
Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90
Polygon -7500403 true true 195 90 240 150 225 180 165 105

false
Rectangle -7500403 true true 135 90 165 300
Polygon -7500403 true true 165 255 210 210 255 195 225 255 165 285
Polygon -7500403 true true 165 180 165 210 225 180 255 120 210 135
Polygon -7500403 true true 165 105 165 135 225 105 255 45 210 60

false
Circle -1 true true 203 65 88
Circle -1 true true 150 105 120
Circle -7500403 true false 214 72 67
Polygon -1 true true 45 285 30 285 30 240 15 195 45 210
Rectangle -1 true true 65 221 80 296
Polygon -7500403 true false 276 85 285 105 302 99 294 83

false
Rectangle -7500403 true true 30 30 270 270
square 2
0
Rectangle -16777216 true false 60 60 240 240
star
0

false
Circle -7500403 true true 0 0 300
Circle -7500403 true true 60 60 180
Circle -7500403 true true 120 120 60
tree
0
Rectangle -6459832 true false 120 195 180 300
Circle -7500403 true true 116 41 127
Circle -7500403 true true 104 74 152
triangle
0

false
Polygon -7500403 true true 150 30 15 255 285 255

false
Rectangle -7500403 true true 4 45 195 187
Rectangle -1 true false 195 60 195 105
Circle -16777216 true false 234 174 42
Circle -16777216 true false 144 174 42
Circle -7500403 false true 24 174 42
Circle -7500403 false true 234 174 42
turtle
0
Polygon -10899396 true false 195 90 225 75 245 75 260 89 269 108 261 124 240 105 225 105 210 105
Polygon -10899396 true false 132 85 134 64 107 51 108 17 150 2 192 18 192 52 169 65 172 87
Polygon -7500403 true true 119 75 179 75 209 101 224 135 220 225 175 261 128 261 81 224 74 135 88 99
wheel
0
Circle -16777216 true false 30 30 240
Line -7500403 true 15 150 285 150
Line -7500403 true 216 40 79 269
Line -7500403 true 40 216 269 79

false
Polygon -16777216 true false 253 133 245 131 245 133
Polygon -7500403 true true -1 195 14 180 36 166 40 153 53 140 82 131 134 133 159 126 188 115 227 108 236 102 238 98 268 86 269 92 281 87 269 103 269 113
x
0
Polygon -7500403 true true 30 75 75 30 270 225 225 270
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
  <experiment name="experiment" repetitions="1" runMetricsEveryStep="false">
    <go>go</go>
    <timeLimit steps="1000"/>
      <value value="&quot;grids/grid_4_poi_3_01.csv&quot;"/>
    <enumeratedValueSet variable="entry-ratio">
    </enumeratedValueSet>
      <value value="0.087"/>
    <enumeratedValueSet variable="global-patience">
    </enumeratedValueSet>
      <value value="true"/>
    <enumeratedValueSet variable="use-entries">
    </enumeratedValueSet>
</experiments>
@#$#@#$#@
0.0
0.0 1 1.0 0.0
link direction
0
Line -7500403 true 150 150 210 180
@#$#@#$#@
@#$#@#$#@
