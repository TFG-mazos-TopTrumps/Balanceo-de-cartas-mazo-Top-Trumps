import { User } from "./User";

export class Deck {
    idDeck: number;
    name: string;
    description: string;
    nCards: number;
    nCategories: number;
    image: string;
    idUser: number;

    public toString() : string {
        return JSON.stringify(this)
    }
}