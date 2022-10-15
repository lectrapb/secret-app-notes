
export interface Response{

    data: any[];
    message: Message;
    errors: Error[];
}

export interface Message{

    source: string;
    detail:string;
    title: string;
    status: string;
}

export interface Error{

     status: number;
     code:string;
     title: string;
     detail: string;
}