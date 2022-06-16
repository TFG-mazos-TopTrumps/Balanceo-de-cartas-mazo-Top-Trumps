import { User } from "./User";

export class Keyword {
    idKeyword: number;
    word: string;

    public toString() : string {
        return JSON.stringify(this)
    }
}