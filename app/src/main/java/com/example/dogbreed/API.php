<?php

// rewrite API use PHP
$dogs = [
    [
        "bred_for" => "Small rodent hunting, lapdog",
        "breed_group" => "Toy",
        "height" => [
            "imperial" => "9 - 11.5",
            "metric" => "23 - 29"
        ],
        "id" => 1,
        "life_span" => "10 - 12 years",
        "name" => "Affenpinscher",
        "origin" => "Germany, France",
        "temperament" => "Stubborn, Curious, Playful, Adventurous, Active, Fun-loving",
        "weight" => [
            "imperial" => "6 - 13",
            "metric" => "3 - 6"
        ],
        "url" => "https://raw.githubusercontent.com/DevTides/DogsApi/master/1.jpg"
    ],
    [
        "bred_for" => "Coursing and hunting",
        "breed_group" => "Hound",
        "country_code" => "AG",
        "height" => [
            "imperial" => "25 - 27",
            "metric" => "64 - 69"
        ],
        "id" => 2,
        "life_span" => "10 - 13 years",
        "name" => "Afghan Hound",
        "origin" => "Afghanistan, Iran, Pakistan",
        "temperament" => "Aloof, Clownish, Dignified, Independent, Happy",
        "weight" => [
            "imperial" => "50 - 60",
            "metric" => "23 - 27"
        ],
        "url" => "https://raw.githubusercontent.com/DevTides/DogsApi/master/2.jpg"
    ]
    [
            "bred_for" => "A wild pack animal",
            "height" => [
                "imperial" => "30",
                "metric" => "76"
            ],
            "id" => 3,
            "life_span" => "11 years",
            "name" => "African Hunting Dog",
            "origin" => "",
            "temperament" => "Wild, Hardworking, Dutiful",
            "weight" => [
                "imperial" => "44 - 66",
                "metric" => "20 - 30"
            ],
            "url" => "https://raw.githubusercontent.com/DevTides/DogsApi/master/3.jpg"
        ],
        [
            "bred_for" => "Badger, otter hunting",
            "breed_group" => "Terrier",
            "height" => [
                "imperial" => "21 - 23",
                "metric" => "53 - 58"
            ],
            "id" => 4,
            "life_span" => "10 - 13 years",
            "name" => "Airedale Terrier",
            "origin" => "United Kingdom, England",
            "temperament" => "Outgoing, Friendly, Alert, Confident, Intelligent, Courageous",
            "weight" => [
                "imperial" => "40 - 65",
                "metric" => "18 - 29"
            ],
            "url" => "https://raw.githubusercontent.com/DevTides/DogsApi/master/4.jpg"
        ],
        [
            "bred_for" => "Sheep guarding",
            "breed_group" => "Working",
            "height" => [
                "imperial" => "28 - 34",
                "metric" => "71 - 86"
            ],
            "id" => 5,
            "life_span" => "10 - 12 years",
            "name" => "Akbash Dog",
            "origin" => "",
            "temperament" => "Loyal, Independent, Intelligent, Brave",
            "weight" => [
                "imperial" => "90 - 120",
                "metric" => "41 - 54"
            ],
            "url" => "https://raw.githubusercontent.com/DevTides/DogsApi/master/5.jpg"
        ],
        [
            "bred_for" => "Hunting bears",
            "breed_group" => "Working",
            "height" => [
                "imperial" => "24 - 28",
                "metric" => "61 - 71"
            ],
            "id" => 6,
            "life_span" => "10 - 14 years",
            "name" => "Akita",
            "temperament" => "Docile, Alert, Responsive, Dignified, Composed, Friendly, Receptive, Faithful, Courageous",
            "weight" => [
                "imperial" => "65 - 115",
                "metric" => "29 - 52"
            ],
            "url" => "https://raw.githubusercontent.com/DevTides/DogsApi/master/6.jpg"
        ]
];

// Thiết lập header của API để thông báo rằng dữ liệu trả về là JSON
header('Content-Type: application/json');

// Trả về dữ liệu dưới dạng JSON
echo json_encode($dogs);
