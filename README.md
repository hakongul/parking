# Parking

## Tjenestens formål
API'et tilbyr utregning av takst for parkeringssonene M1, M2 og M3 via en webservice.


## Tjenesten takst
Tjenesten nås ved å kalle adressen /takst

Det må sendes med aktuell parkeringssone og antall minutter det skal beregnes for.

Resultatet returneres som JSON. Se Respons for format

### Eksempel

```/takst?sone=M2&minutter=75```

#### Respons
```
{
    "sone": "M2",
    "pris": 400
}
```
