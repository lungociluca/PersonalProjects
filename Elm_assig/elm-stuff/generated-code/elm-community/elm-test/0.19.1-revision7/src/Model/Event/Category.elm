module Model.Event.Category exposing (EventCategory(..), SelectedEventCategories, allSelected, eventCategories, isEventCategorySelected, set, view)

import Html exposing (Html, div, input, text)
import Html.Attributes exposing (checked, class, style, type_)
import Html.Events exposing (onCheck)


type EventCategory
    = Academic
    | Work
    | Project
    | Award


eventCategories =
    [ Academic, Work, Project, Award ]

{-| Type used to represent the state of the selected event categories
-}
type SelectedEventCategories
    = SelectedEventCategories {academic: Bool, work: Bool, project: Bool, award: Bool}

{-| Returns an instance of `SelectedEventCategories` with all categories selected

    isEventCategorySelected Academic allSelected --> True

-}
allSelected : SelectedEventCategories
allSelected =
    SelectedEventCategories {academic=True, work=True, project=True, award=True}


{-| Given a the current state and a `category` it returns whether the `category` is selected.

    isEventCategorySelected Academic allSelected --> True

-}

getAcademic: SelectedEventCategories -> Bool
getAcademic (SelectedEventCategories a) = a.academic

getWork: SelectedEventCategories -> Bool
getWork (SelectedEventCategories a) = a.work

getProject: SelectedEventCategories -> Bool
getProject (SelectedEventCategories a) = a.project

getAward: SelectedEventCategories -> Bool
getAward (SelectedEventCategories a) = a.award

isEventCategorySelected : EventCategory -> SelectedEventCategories -> Bool
isEventCategorySelected category current =
    case category of
       Academic -> getAcademic current
       Work -> getWork current
       Project -> getProject current
       Award -> getAward current


{-| Given an `category`, a boolean `value` and the current state, it sets the given `category` in `current` to `value`.

    allSelected |> set Academic False |> isEventCategorySelected Academic --> False

    allSelected |> set Academic False |> isEventCategorySelected Work --> True

-}

set : EventCategory -> Bool -> SelectedEventCategories -> SelectedEventCategories
set category value current =
    let
        academicVal = getAcademic current
        projectVal = getProject current
        workVal = getWork current
        awardVal = getAward current
    in
        case category of
            Academic -> SelectedEventCategories {academic = value, work = workVal, project = projectVal, award = awardVal}
            Work -> SelectedEventCategories {academic = academicVal, work = value, project = projectVal, award = awardVal}
            Project -> SelectedEventCategories {academic = academicVal, work = workVal, project = value, award = awardVal}
            Award -> SelectedEventCategories {academic = academicVal, work = workVal, project = projectVal, award = value}


checkbox : String -> Bool -> EventCategory -> Html ( EventCategory, Bool )
checkbox name state category =
    div [ style "display" "inline", class "category-checkbox" ]
        [ input [ type_ "checkbox", onCheck (\c -> ( category, c )), checked state ] []
        , text name
        ]


view : SelectedEventCategories -> Html ( EventCategory, Bool )
view model =
    div [] 
    [
         checkbox "Academic" (getAcademic model) Academic,
         checkbox "Work" (getWork model) Work,
         checkbox "Project" (getProject model) Project,
         checkbox "Award" (getAward model) Award
    ]
