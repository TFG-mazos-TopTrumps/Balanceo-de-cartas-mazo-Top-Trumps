export class User {

    idUser:number;
    username:string;
    password:string;
    name:string;

    public toString() : string {
        return JSON.stringify(this)
    }

}