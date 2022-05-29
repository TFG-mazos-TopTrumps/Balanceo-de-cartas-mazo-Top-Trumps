export class User {

    usuario:string;
    password:string;
    name:string;

    public toString() : string {
        return JSON.stringify(this)
    }

}