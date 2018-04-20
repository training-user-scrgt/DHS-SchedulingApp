export class User {
    // Note: Using only optional constructor properties without backing store disables typescript's type checking for the type
    constructor(userId?: string, FirstName?: string, LastName?: string, username?: string) {

        this.userId = userId;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.username = username;
    }

    public userId: string;
    public FirstName: string;
    public LastName: string;
    public username: string;
}
