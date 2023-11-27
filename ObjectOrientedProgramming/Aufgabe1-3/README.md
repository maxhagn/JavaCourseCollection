# Konzept
## Model und Wald
Ein Model enthält einen Wald, eine Liste an Wetterdaten und eine Liste von Bauplänen. ClimateDataCollection enthält Wetterbedingungen zu einem bestimmten Zeitpunkt. BuildingPlanData enthält Bebauungspläne der Waldeigentümer.
Wird eine Simulation mit einem Modell gestartet wird durch alle Wetterdaten iteriert, wobei die SimulationDensity angibt, wie viele Werte pro Jahr vorhanden sind. Für jeden Zeitpunkt an Wetterdaten entwickelt sich der Wald. Jedes Jahr werden die Bauvorhaben der Waldbesitzer umgesetzt, wodurch mehr Wege und Gebäude gebaut werden.
Ein Wald enthält weiteres eine Liste an Bäumen und einen Waldboden. Der Waldboden besteht aus Bebauungen und Fläche für Bäume. Des Weiteren kann der Waldboden Wasser aus Niederschlägen und abgeschnittene oder abgefallene Baumstücke speichern.
## Bäume
Ein Baum besitzt ein Alter, eine Höhe und einen Gesundheitswert zwischen 0 und 1. Altert ein Baum, um eine bestimmte Zeitspanne wird Wasser verbraucht und aus den Wetterdaten ergeben sich bestimmte Multiplikatoren die das Wachstum eines Baumes bestimmten.
Bei Sturm oder Abholzung wird ein gewisser Betrag der Höhe weggenommen und dies wirkt sich somit negativ auf das Wachstum aus. Fällt die Gesundheit eines Baumes nahezu auf null oder wird ein Baum komplett gefällt, wird er aus der Liste gelöscht. Neue Bäume
können entstehen, wenn genug unbebaute Waldfläche vorhanden ist und die Gesamtgesundheit des Waldes gut ist. Je nach Reproduktionsfaktor der verschiedenen Bäume entsteht ein neuer Baum dieses Typs auf der leeren Fläche.
## Klimadaten
Eine ClimateDataCollection enthält Wetterdaten zu einem Zeitpunkt x. Diese werden verwendet, um das Wachstum eines Baumes zu beeinflussen.
## Baupläne
In einem Wald können Wege und Gebäude gebaut werden. Ein BuildingPlanData enthält Informationen darüber ob Wege oder Gebäude gebaut werden und wie viel Fläche diese in m^2 in Anspruch nehmen. 
