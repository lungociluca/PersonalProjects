module Model.Event exposing (..)

import Html exposing (..)
import Html.Attributes exposing (class, classList)
import Model.Event.Category exposing (EventCategory(..))
import Model.Interval as Interval exposing (Interval)
import Json.Decode exposing (bool)


type alias Event =
    { title : String
    , interval : Interval
    , description : Html Never
    , category : EventCategory
    , url : Maybe String
    , tags : List String
    , important : Bool
    }


categoryView : EventCategory -> Html Never
categoryView category =
    case category of
        Academic ->
            text "Academic"

        Work ->
            text "Work"

        Project ->
            text "Project"

        Award ->
            text "Award"

compareInterv: (Interval, Event) -> (Interval, Event) -> Order
compareInterv (i1, e1) (i2, e2) = Interval.compare i1 i2

getEvent: (Interval, Event) -> Event
getEvent (i, e) = e

sortByInterval : List Event -> List Event
sortByInterval events =
    events |> 
    List.map (\x -> (x.interval, x)) |>
    List.sortWith (compareInterv) |>
    List.map(getEvent)

getEventClass: Event -> String
getEventClass event = if event.important == True then "event event-important" else "event"

intervalToString: Interval -> String
intervalToString interval = (Interval.startString <| Interval.getStart interval) ++ (Interval.endString <| Interval.getEnd interval)

view : Event -> Html Never
view event =
    div [] 
    [
        div [class (getEventClass event)] 
        [
        p [class "event-title"] [text event.title],
        p [class "event-description"] [event.description],
        p [class "event-category"] [categoryView (event.category)],
        p [class "event-url"] [text ""],
        p [class "event-interval"] [text (intervalToString(event.interval))] 
        ]
    ]
