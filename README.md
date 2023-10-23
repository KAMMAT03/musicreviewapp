# Music Review APP
This is the backend for [Music Review APP](http://musicreviewapp.surge.sh) that provides the API.

### Technologies used:
- Java
- Spring Boot
- PosgreSQL
- AWS ElasticBeanstalk and RDS
- Spotify API

### Usage of Spotify API
In order for the user to be able to search any album they want and add a review to it, I needed a database with almost any album that was commercialy released.
For obvious reasons it would be very hard and impractical to implement such database on my own, so I decided to use Spotify's API, which provides all the necessary methods to get the information that I need.

### Hosting
The API itself is deployed on AWS ElasticBeanstalk, which I found to be the most suitable for this project and the database is hosted on AWS RDS.

# HTTP methods
## Album
### Search an album - GET
http://musicreviewapp.eu-north-1.elasticbeanstalk.com/api/albums/search?content={searchContent}

Params:
- content (required)
- pageNo
- pageSize

<details>
  <summary>Response example:</summary>
  
  ```json 
{
    "pageNo": 1,
    "pageSize": 10,
    "totalElements": 50,
    "totalPages": 5,
    "last": false,
    "content": [
        {
            "id": "4eLPsYPBmXABThSJ821sqY",
            "name": "DAMN.",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b2738b52c6b9bc4e43d873869699"
        },
        {
            "id": "79ONNoS4M9tfIA1mYLBYVX",
            "name": "Mr. Morale & The Big Steppers",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b2732e02117d76426a08ac7c174f"
        },
        {
            "id": "0Oq3mWfexhsjUh0aNNBB5u",
            "name": "good kid, m.A.A.d city",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b2732cd55246d935a8a77cb4859e"
        },
        {
            "id": "748dZDqSZy6aPXKcI9H80u",
            "name": "good kid, m.A.A.d city (Deluxe)",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b27378de8b28de36a74afc0348b5"
        },
        {
            "id": "1bkN9nIkkCnXeG4yitVS1J",
            "name": "Section.80",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b273eddb2639b74ac6c202032ebe"
        },
        {
            "id": "7ycBtnsMtyVbbwTfJwRjSP",
            "name": "To Pimp A Butterfly",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b273cdb645498cd3d8a2db4d05e1"
        },
        {
            "id": "3pLdWdkj83EYfDN6H2N8MR",
            "name": "Black Panther The Album Music From And Inspired By",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                },
                {
                    "id": "7tYKF4w9nC0nq9CsPZTHyP",
                    "name": "SZA"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b273c027ad28821777b00dcaa888"
        },
        {
            "id": "2XPrwlaAHHXnJzP9tBcIzH",
            "name": "Swimming Pools (Drank)",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b273b5ef185d28724c5573c2ac9c"
        },
        {
            "id": "0kL3TYRsSXnu0iJvFO3rud",
            "name": "untitled unmastered.",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b2738c697f553a46006a5d8886b2"
        },
        {
            "id": "7MoLQ8vckhwBbQqEYQTYQC",
            "name": "Overly Dedicated",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b2739b035b031d9f0a6a75ae464e"
        }
    ]
}
  ```
</details>

### Get album by id - GET
http://musicreviewapp.eu-north-1.elasticbeanstalk.com/api/albums/{albumId}

<details>
  <summary>Response example:</summary>
  
  ```json
{
    "id": "0Oq3mWfexhsjUh0aNNBB5u",
    "name": "good kid, m.A.A.d city",
    "artists": [
        {
            "id": "2YZyLoL8N0Wb9xBt1NhZWg",
            "name": "Kendrick Lamar"
        }
    ],
    "releaseDate": "2012",
    "imageUrl": "https://i.scdn.co/image/ab67616d0000b2732cd55246d935a8a77cb4859e",
    "trackList": [
        {
            "id": "3KHjXNQDEZ0Hb2hzGdxcx7",
            "name": "Sherane a.k.a Master Splinter’s Daughter",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ]
        },
        {
            "id": "0yhMmqax6HRAZxI7udEask",
            "name": "Bitch, Don’t Kill My Vibe",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ]
        },
        {
            "id": "0vjKXtFLt6Ti08Y3JCnRKB",
            "name": "Backseat Freestyle",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ]
        },
        {
            "id": "4Gf6VlNy2E4VVuAkl0y2XW",
            "name": "The Art of Peer Pressure",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ]
        },
        {
            "id": "74tLlkN3rgVzRqQJgPfink",
            "name": "Money Trees",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                },
                {
                    "id": "28ExwzUQsvgJooOI0X1mr3",
                    "name": "Jay Rock"
                }
            ]
        },
        {
            "id": "5J6CXHvsfRGyyCnQZmVPoE",
            "name": "Poetic Justice",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                },
                {
                    "id": "3TVXtAsR1Inumwj472S9r4",
                    "name": "Drake"
                }
            ]
        },
        {
            "id": "3yapR7DctB2WtbmXkHHviC",
            "name": "good kid",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ]
        },
        {
            "id": "0nfQguNoAfKFjsQHr6qnxc",
            "name": "m.A.A.d city",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                },
                {
                    "id": "4XqfpACObRB5AsBcUYjL8X",
                    "name": "MC Eiht"
                }
            ]
        },
        {
            "id": "3VYzbU22BooAROigHHwycs",
            "name": "Swimming Pools (Drank) - Extended Version",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ]
        },
        {
            "id": "69pkT3GewmjhQf6vQWcK2L",
            "name": "Sing About Me, I'm Dying Of Thirst",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ]
        },
        {
            "id": "2MYm1yy4bYba3mGF9Ou6Yo",
            "name": "Real",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                },
                {
                    "id": "0N41KJ4H6bkPAm2tx7VS8C",
                    "name": "Anna Wise"
                }
            ]
        },
        {
            "id": "3i2XxFTLd2061UISvE5W7f",
            "name": "Compton",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                },
                {
                    "id": "6DPYiyq5kWVQS4RGwxzPC7",
                    "name": "Dr. Dre"
                }
            ]
        },
        {
            "id": "3SImXx6qmobDK2q7MKeR1Z",
            "name": "Bitch, Don’t Kill My Vibe - Remix",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                },
                {
                    "id": "3nFkdlSjzX9mRTtwJOzDYB",
                    "name": "JAY-Z"
                }
            ]
        },
        {
            "id": "6WfA83OCEsiZ2IOTbUF4UQ",
            "name": "Bitch, Don’t Kill My Vibe - International Remix / Explicit Version",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                },
                {
                    "id": "7sfgqEdoeBTjd8lQsPT3Cy",
                    "name": "Emeli Sandé"
                }
            ]
        }
    ]
}
  ```
</details>

## Artist
### Get artist by id - GET
http://musicreviewapp.eu-north-1.elasticbeanstalk.com/api/artist/{artistId}

<details>
  <summary>Response example:</summary>

  ```json
{
    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
    "name": "Kendrick Lamar",
    "imageUrl": "https://i.scdn.co/image/ab6761610000e5eb437b9e2a82505b3d93ff1022",
    "albums": [
        {
            "id": "79ONNoS4M9tfIA1mYLBYVX",
            "name": "Mr. Morale & The Big Steppers",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b2732e02117d76426a08ac7c174f"
        },
        {
            "id": "3pLdWdkj83EYfDN6H2N8MR",
            "name": "Black Panther The Album Music From And Inspired By",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                },
                {
                    "id": "7tYKF4w9nC0nq9CsPZTHyP",
                    "name": "SZA"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b273c027ad28821777b00dcaa888"
        },
        {
            "id": "4alcGHjstaALJHHiljfy3H",
            "name": "DAMN. COLLECTORS EDITION.",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b273add9eb25744782c3717c9368"
        },
        {
            "id": "4eLPsYPBmXABThSJ821sqY",
            "name": "DAMN.",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b2738b52c6b9bc4e43d873869699"
        },
        {
            "id": "0kL3TYRsSXnu0iJvFO3rud",
            "name": "untitled unmastered.",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b2738c697f553a46006a5d8886b2"
        },
        {
            "id": "7ycBtnsMtyVbbwTfJwRjSP",
            "name": "To Pimp A Butterfly",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b273cdb645498cd3d8a2db4d05e1"
        },
        {
            "id": "748dZDqSZy6aPXKcI9H80u",
            "name": "good kid, m.A.A.d city (Deluxe)",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b27378de8b28de36a74afc0348b5"
        },
        {
            "id": "0Oq3mWfexhsjUh0aNNBB5u",
            "name": "good kid, m.A.A.d city",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b2732cd55246d935a8a77cb4859e"
        },
        {
            "id": "1bkN9nIkkCnXeG4yitVS1J",
            "name": "Section.80",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b273eddb2639b74ac6c202032ebe"
        },
        {
            "id": "7MoLQ8vckhwBbQqEYQTYQC",
            "name": "Overly Dedicated",
            "artists": [
                {
                    "id": "2YZyLoL8N0Wb9xBt1NhZWg",
                    "name": "Kendrick Lamar"
                }
            ],
            "imageUrl": "https://i.scdn.co/image/ab67616d0000b2739b035b031d9f0a6a75ae464e"
        }
    ]
}
```
</details>

## Review
### Create review - POST
http://musicreviewapp.eu-north-1.elasticbeanstalk.com/api/reviews/create

Authorization: Bearer Token

<details>
  <summary>Request body example:</summary>
  
> Note: {reviewScore} must be an integer between 1 and 10

  ```json
{
    "albumId": "{albumId}",
    "title": "{reviewTitle}",
    "content": "{reviewContent}",
    "score": "{reviewScore}"
}
  ```
</details>

<details>
  <summary>Response example:</summary>

  > Note: review likes have yet to be implemented

  ```json
{
    "id": "{reviewId}",
    "albumId": "{albumId}",
    "title": "{reviewTitle}",
    "content": "{reviewContent}",
    "score": "{reviewScore}",
    "likes": "{likesNo}",
    "dateOfPublication": "{dateOfPublication}",
    "username": "{username}"
}
  ```
</details>

### Get review by its id - GET
http://musicreviewapp.eu-north-1.elasticbeanstalk.com/api/reviews/{reviewId}

<details>
  <summary>Response example:</summary>

  > Note: review likes have yet to be implemented

  ```json
{
    "id": "{reviewId}",
    "albumId": "{albumId}",
    "title": "{reviewTitle}",
    "content": "{reviewContent}",
    "score": "{reviewScore}",
    "likes": "{likesNo}",
    "dateOfPublication": "{dateOfPublication}",
    "username": "{username}"
}
  ```
</details>

### Get reviews of a certain album - GET
http://musicreviewapp.eu-north-1.elasticbeanstalk.com/api/albums/{albumId}/reviews

Params:
- pageNo
- pageSize

<details>
  <summary>Response example:</summary>

  > Note: review likes have yet to be implemented

  ```json
{
    "pageNo": 1,
    "pageSize": 10,
    "totalElements": 4,
    "totalPages": 1,
    "last": true,
    "content": [
        {
            "id": 6,
            "albumId": "41GuZcammIkupMPKH2OJ6I",
            "title": "The Evolution of Travis",
            "content": "\"Astroworld\" marks a significant step in Travis Scott's evolution as an artist. It's a versatile and groundbreaking piece of work.",
            "score": 8,
            "likes": 0,
            "dateOfPublication": "2023-10-20T20:50:11.755245",
            "username": "Louis"
        },
        {
            "id": 5,
            "albumId": "41GuZcammIkupMPKH2OJ6I",
            "title": "The Carnival of Rap",
            "content": "Travis Scott's \"Astroworld\" is a musical carnival of epic proportions. Each track is like a thrilling ride. You won't want to get off.",
            "score": 7,
            "likes": 0,
            "dateOfPublication": "2023-10-20T20:49:28.431624",
            "username": "Mark"
        },
        {
            "id": 4,
            "albumId": "41GuZcammIkupMPKH2OJ6I",
            "title": "Bumping in My Headphones",
            "content": "\"Astroworld\" is a non-stop banger! Travis Scott's signature sound is in full force, making this album an instant classic for hip-hop lovers.",
            "score": 8,
            "likes": 0,
            "dateOfPublication": "2023-10-20T20:49:03.50382",
            "username": "David"
        },
        {
            "id": 3,
            "albumId": "41GuZcammIkupMPKH2OJ6I",
            "title": "Astroworld: An Astronomical Journey",
            "content": "Travis Scott takes us on an interstellar adventure through \"Astroworld.\" Mind-bending beats and lyrics that orbit the cosmos. A masterpiece.",
            "score": 9,
            "likes": 0,
            "dateOfPublication": "2023-10-20T20:48:37.550179",
            "username": "John"
        }
    ]
}
  ```
</details>

### Get users reviews - GET
http://musicreviewapp.eu-north-1.elasticbeanstalk.com/api/users/{username}/reviews

Params:
- pageNo
- pageSize

<details>
  <summary>Response example:</summary>

  > Note: review likes have yet to be implemented

  ```json
{
    "pageNo": 1,
    "pageSize": 10,
    "totalElements": 5,
    "totalPages": 1,
    "last": true,
    "content": [
        {
            "id": 6,
            "albumId": "41GuZcammIkupMPKH2OJ6I",
            "albumDetails": {
                "id": "41GuZcammIkupMPKH2OJ6I",
                "name": "ASTROWORLD",
                "artists": [
                    {
                        "id": "0Y5tJX1MQlPlqiwlOH1tJY",
                        "name": "Travis Scott"
                    }
                ],
                "imageUrl": "https://i.scdn.co/image/ab67616d0000b273072e9faef2ef7b6db63834a3"
            },
            "title": "The Evolution of Travis",
            "content": "\"Astroworld\" marks a significant step in Travis Scott's evolution as an artist. It's a versatile and groundbreaking piece of work.",
            "score": 8,
            "likes": 0,
            "dateOfPublication": "2023-10-20T20:50:11.755245",
            "username": "Kamil"
        },
        {
            "id": 5,
            "albumId": "41GuZcammIkupMPKH2OJ6I",
            "albumDetails": {
                "id": "41GuZcammIkupMPKH2OJ6I",
                "name": "ASTROWORLD",
                "artists": [
                    {
                        "id": "0Y5tJX1MQlPlqiwlOH1tJY",
                        "name": "Travis Scott"
                    }
                ],
                "imageUrl": "https://i.scdn.co/image/ab67616d0000b273072e9faef2ef7b6db63834a3"
            },
            "title": "The Carnival of Rap",
            "content": "Travis Scott's \"Astroworld\" is a musical carnival of epic proportions. Each track is like a thrilling ride. You won't want to get off.",
            "score": 7,
            "likes": 0,
            "dateOfPublication": "2023-10-20T20:49:28.431624",
            "username": "Kamil"
        },
        {
            "id": 4,
            "albumId": "41GuZcammIkupMPKH2OJ6I",
            "albumDetails": {
                "id": "41GuZcammIkupMPKH2OJ6I",
                "name": "ASTROWORLD",
                "artists": [
                    {
                        "id": "0Y5tJX1MQlPlqiwlOH1tJY",
                        "name": "Travis Scott"
                    }
                ],
                "imageUrl": "https://i.scdn.co/image/ab67616d0000b273072e9faef2ef7b6db63834a3"
            },
            "title": "Bumping in My Headphones",
            "content": "\"Astroworld\" is a non-stop banger! Travis Scott's signature sound is in full force, making this album an instant classic for hip-hop lovers.",
            "score": 8,
            "likes": 0,
            "dateOfPublication": "2023-10-20T20:49:03.50382",
            "username": "Kamil"
        },
        {
            "id": 3,
            "albumId": "41GuZcammIkupMPKH2OJ6I",
            "albumDetails": {
                "id": "41GuZcammIkupMPKH2OJ6I",
                "name": "ASTROWORLD",
                "artists": [
                    {
                        "id": "0Y5tJX1MQlPlqiwlOH1tJY",
                        "name": "Travis Scott"
                    }
                ],
                "imageUrl": "https://i.scdn.co/image/ab67616d0000b273072e9faef2ef7b6db63834a3"
            },
            "title": "Astroworld: An Astronomical Journey",
            "content": "Travis Scott takes us on an interstellar adventure through \"Astroworld.\" Mind-bending beats and lyrics that orbit the cosmos. A masterpiece.",
            "score": 9,
            "likes": 0,
            "dateOfPublication": "2023-10-20T20:48:37.550179",
            "username": "Kamil"
        },
        {
            "id": 2,
            "albumId": "2Lq2qX3hYhiuPckC8Flj21",
            "albumDetails": {
                "id": "2Lq2qX3hYhiuPckC8Flj21",
                "name": "Master Of Puppets (Remastered)",
                "artists": [
                    {
                        "id": "2ye2Wgw4gimLv2eAKyk1NB",
                        "name": "Metallica"
                    }
                ],
                "imageUrl": "https://i.scdn.co/image/ab67616d0000b273668e3aca3167e6e569a9aa20"
            },
            "title": "The best metal album ever made!!!",
            "content": "Everything about it is great, riffs, solos, vocals, drums, bass, simply perfect. It is awesome!",
            "score": 10,
            "likes": 0,
            "dateOfPublication": "2023-10-20T20:42:48.885928",
            "username": "Kamil"
        }
    ]
}
  ```
</details>

### Update review - PUT
http://musicreviewapp.eu-north-1.elasticbeanstalk.com/api/reviews/{reviewId}/update

Authorization: Bearer Token

<details>
  <summary>Request body example:</summary>

  > Note: you only have to include those properties that are to be updated

```json
{
    "albumId": "{albumId}",
    "title": "{reviewTitle}",
    "content": "{reviewContent}",
    "score": "{reviewScore}"
}
``` 
</details>

<details>
  <summary>Response example:</summary>

  > Note: review likes have yet to be implemented

  ```json
{
    "id": "{reviewId}",
    "albumId": "{albumId}",
    "title": "{reviewTitle}",
    "content": "{reviewContent}",
    "score": "{reviewScore}",
    "likes": "{likesNo}",
    "dateOfPublication": "{dateOfPublication}",
    "username": "{username}"
}
  ```
</details>

### Delete review - DELETE
http://musicreviewapp.eu-north-1.elasticbeanstalk.com/api/reviews/{reviewId}/delete

Authorization: Bearer Token

<details>
  <summary>Response example:</summary>

```json
{
    "Review deleted successfully"
}
``` 
</details>

## Login/Register
### Register - POST
http://musicreviewapp.eu-north-1.elasticbeanstalk.com/api/auth/register

<details>
  <summary>Request body example:</summary>

```json
{
    "username": "{username}",
    "password": "{password}"
}
``` 
</details>

<details>
  <summary>Response example:</summary>

  ```json
{
    "code": "200",
    "token": "{bearerToken}"
}
  ```
</details>

### Login - POST
http://musicreviewapp.eu-north-1.elasticbeanstalk.com/api/auth/login

<details>
  <summary>Request body example:</summary>

```json
{
    "username": "{username}",
    "password": "{password}"
}
``` 
</details>

<details>
  <summary>Response example:</summary>

  ```json
{
    "accessToken": "{bearerToken}",
    "tokenType": "Bearer "
}
  ```
</details>
