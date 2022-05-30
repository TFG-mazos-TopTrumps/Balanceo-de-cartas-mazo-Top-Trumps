export class User {

    username:string;
    password:string;
    name:string;

    public toString() : string {
        return JSON.stringify(this)
    }

}