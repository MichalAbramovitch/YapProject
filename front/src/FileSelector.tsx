import * as React from "react";
import './fileSelector.css';
import { FileService } from "./FileService";


export type FileSelectorState = {
    file: any
}
export class FileSelector extends React.Component<{}, FileSelectorState>
{
    fileService: FileService;
  

    constructor(props: any)
    {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.fileUpload = this.fileUpload.bind(this);
        this.fileService = new FileService();
    }

    state: FileSelectorState = {
        file: null
    }
    
        fileUpload = () => {
        const data = new FormData();
        //using File API to get chosen file
        let file = this.state.file
        data.append('file', file);
        data.append('name', 'my_file');
        data.append('description', 'this file is uploaded by young padawan');
        let self = this;
        //calling async Promise and handling response or error situation
        this.fileService.uploadFileToServer(data).then((response : any) => {
            console.log("File " + file.name + " is uploaded");
        }).catch(function (error : any) {
            console.log(error);
            if (error.response) {
                //HTTP error happened
                console.log("Upload error. HTTP error/status code=",error.response.status);
            } else {
                //some other error happened
               console.log("Upload error. HTTP error/status code=",error.message);
            }
        });
    };
    
    handleChange(selectorFiles: any)
    {
        this.setState({file: selectorFiles[0]})
    }

    render ()
    {
        return (<div className = 'file-select-container'>
            <input type="file" onChange={ (e) => this.handleChange(e.target.files) } />
            <button type="submit" onClick={this.fileUpload}>Upload</button>
        </div>);
    }
}
export default FileSelector;
