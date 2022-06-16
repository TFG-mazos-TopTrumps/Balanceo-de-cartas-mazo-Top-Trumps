export class Card {
    idCard: number;
    name: string;
    description: string;
    image: string;
    idDeck: number;

    public toString() : string {
        return JSON.stringify(this)
    }
}

