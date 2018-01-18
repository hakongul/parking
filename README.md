# Parking

## Tjenestens formål
API'et tilbyr utregning av takst for parkeringssonene M1, M2 og M3 via en webservice.


## Tjenesten takst
Tjenesten nås ved å kalle adressen /takst

Det må sendes med aktuell parkeringssone og antall minutter det skal beregnes for.

Resultatet returneres som JSON. Se Respons for format

### Eksempel sone 1 

```/takst?sone=M1&minutter=46 ```

#### Respons
```
{
    "sone": "M1",
    "pris": 46
}
```

### Eksempel sone 2 hverdagstakst

```/takst?sone=M2&minutter=75```

#### Respons
```
{
    "sone": "M2",
    "pris": 200
}
```

### Eksempel sone 2 helgetakst

```/takst?sone=M2&minutter=75```

#### Respons
```
{
    "sone": "M2",
    "pris": 400
}
```

### Eksempel sone 3 dagtid Mandag - Lørdag

```/takst?sone=M3&minutter=90```

#### Respons
```
{
    "sone": "M3",
    "pris": 60
}
```

### Eksempel sone 3 kveldstid Mandag - Lørdag

```/takst?sone=M3&minutter=90```

#### Respons
```
{
    "sone": "M3",
    "pris": 270
}
```