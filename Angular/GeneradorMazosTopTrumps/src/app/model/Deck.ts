export class Deck {
    idDeck: number;
    name: string;
    description: string;
    ncards: number;
    ncategories: number;
    image: string;
    idUser: number;

    public toString() : string {
        return JSON.stringify(this)
    }
}