export class User{

    constructor(
        public name: string, 
        public email: string,
        public password: string, 
        public role?: string,
        public image?: string,
        public google?: string, 
        public token?: string,
        public uid?: string, 
    ){
        this.role = "USER";
    }
}