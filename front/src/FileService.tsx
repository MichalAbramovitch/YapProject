import Service from "./ service";

export class FileService {
    uploadFileToServer(data: any){
        //returns Promise object
        return Service.getRestClient().post('/files', data);
    }
}
export default FileService