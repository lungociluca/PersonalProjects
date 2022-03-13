module Main exposing (..)

import Browser
import Html exposing (..)
import Html.Attributes exposing (style)
import Html.Events exposing (..)
import Http
import Json.Decode as De
import Model exposing (..)
import Model.Event as Event
import Model.Event.Category as EventCategory
import Model.PersonalDetails as PersonalDetails
import Model.Repo as Repo exposing(..)


type Msg
    = GetRepos
    | GotRepos (Result Http.Error (List Repo.Repo))
    | SelectEventCategory EventCategory.EventCategory
    | DeselectEventCategory EventCategory.EventCategory



main : Program () Model Msg
main =
    Browser.element
        { init = init
        , view = view
        , update = update
        , subscriptions = subscriptions
        }

getRepos : Cmd Msg
getRepos = Http.get 
    { url = "https://api.github.com/users/microsoft/repos"
    , expect = Http.expectJson GotRepos (De.list Repo.decodeRepo) 
    } 

init : () -> ( Model, Cmd Msg )
init _ =
    ( initModel
    , getRepos
    )


subscriptions : Model -> Sub Msg
subscriptions model =
    Sub.none

assignRepos: Model -> List Repo -> Model
assignRepos model reposToAssign = {model | repos = reposToAssign}

selectCategory: Model -> EventCategory.EventCategory -> Model
selectCategory model category = {model | selectedEventCategories = EventCategory.set category True model.selectedEventCategories}

delectCategory: Model -> EventCategory.EventCategory -> Model
delectCategory model category = {model | selectedEventCategories = EventCategory.set category False model.selectedEventCategories}

update : Msg -> Model -> ( Model, Cmd Msg )
update msg model =
    case msg of
        GetRepos ->
            ( initModel, getRepos )

        GotRepos (Ok repos) ->
            ( assignRepos model repos, Cmd.none )

        GotRepos (Err _) ->
            ( model, Cmd.none)

        SelectEventCategory category ->
            ( selectCategory model category, Cmd.none )

        DeselectEventCategory category ->
            ( delectCategory model category, Cmd.none )


eventCategoryToMsg : ( EventCategory.EventCategory, Bool ) -> Msg
eventCategoryToMsg ( event, selected ) =
    if selected then
        SelectEventCategory event

    else
        DeselectEventCategory event


view : Model -> Html Msg
view model =
    let
        eventCategoriesView =
            EventCategory.view model.selectedEventCategories |> Html.map eventCategoryToMsg

        eventsView =
            model.events
                |> List.filter (.category >> (\cat -> EventCategory.isEventCategorySelected cat model.selectedEventCategories))
                |> List.map Event.view
                |> div []
                |> Html.map never

        reposView =
            model.repos
                |> Repo.sortByStars
                |> List.take 5
                |> List.map Repo.view
                |> div []
    in
    div []
        [ PersonalDetails.view model.personalDetails
        , h2 [] [ text "Experience" ]
        , eventCategoriesView
        , eventsView
        , h2 [] [ text "My top repos" ]
        , reposView
        ]
