export class Building {
    // Note: Using only optional constructor properties without backing store disables typescript's type checking for the type
    constructor(buildingId?: number, buildingName?: string) {

        this.buildingId = buildingId;
        this.buildingName = buildingName;
    }

    public buildingId: number;
    public buildingName: string;
}
