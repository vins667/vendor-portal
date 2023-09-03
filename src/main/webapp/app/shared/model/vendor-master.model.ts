export interface IVendorMaster {
    id?: number;
    code?: string;
    description?: string;
}

export class VendorMaster implements IVendorMaster {
    constructor(public id?: number, public code?: string, public description?: string) {}
}
