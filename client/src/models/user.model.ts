
export class User {
    // Note: Using only optional constructor properties without backing store disables typescript's type checking for the type
    constructor(id?: string, firstName?: string, lastName?: string, userName?: string) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public id: string;
    public firstName: string;
    public lastName: string;
    public userName: string;
}