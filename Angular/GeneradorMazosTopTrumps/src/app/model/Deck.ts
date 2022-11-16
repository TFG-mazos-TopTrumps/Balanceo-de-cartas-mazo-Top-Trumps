export class Deck {
    idDeck: number;
    name: string;
    description: string;
    ncards: number;
    ncategories: number;
    image: string;
    borde: string;
    fondo: string;
    panel: string;
    fuente: string;
    idUser: number;

    public toString() : string {
        return JSON.stringify(this)
    }
}