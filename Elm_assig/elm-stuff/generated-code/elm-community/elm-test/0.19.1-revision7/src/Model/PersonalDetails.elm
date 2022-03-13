module Model.PersonalDetails exposing (..)

import Html exposing (..)
import Html.Attributes exposing (class, classList, id, href)


type alias DetailWithName =
    { name : String
    , detail : String
    }


type alias PersonalDetails =
    { name : String
    , contacts : List DetailWithName
    , intro : String
    , socials : List DetailWithName
    }
view : PersonalDetails -> Html msg
view details = div [] 
    [
        h1 [id "name"] [text (details.name)],
        em [id "intro"] [text (details.intro)],
        ul [] <| List.map (\record -> li [ class "contact-detail"] [ text record.detail ]) details.contacts,
        ul [] <| List.map (\record -> li [ class "social-link"] [ a [href record.detail] [text record.name] ]) details.socials
    ]
